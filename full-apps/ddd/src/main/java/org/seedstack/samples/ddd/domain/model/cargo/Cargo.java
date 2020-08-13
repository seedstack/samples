/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.ddd.domain.model.cargo;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import org.apache.commons.lang3.Validate;
import org.seedstack.business.domain.BaseAggregateRoot;
import org.seedstack.business.domain.Identity;
import org.seedstack.samples.ddd.domain.model.handling.HandlingEvent;
import org.seedstack.samples.ddd.domain.model.handling.HandlingHistory;
import org.seedstack.samples.ddd.domain.model.location.UnLocode;

/**
 * A Cargo. This is the central class in the domain model,
 * and it is the root of the Cargo-Itinerary-Leg-Delivery-RouteSpecification aggregate.
 *
 * A cargo is identified by a unique tracking id, and it always has an origin
 * and a route specification. The life cycle of a cargo begins with the booking procedure,
 * when the tracking id is assigned. During a (short) period of time, between booking
 * and initial routing, the cargo has no itinerary.
 *
 * The booking clerk requests a list of possible routes, matching the route specification,
 * and assigns the cargo to one route. The route to which a cargo is assigned is described
 * by an itinerary.
 *
 * A cargo can be re-routed during transport, on demand of the customer, in which case
 * a new route is specified for the cargo and a new route is requested. The old itinerary,
 * being a value object, is discarded and a new one is attached.
 *
 * It may also happen that a cargo is accidentally misrouted, which should notify the proper
 * personnel and also trigger a re-routing procedure.
 *
 * When a cargo is handled, the status of the delivery changes. Everything about the delivery
 * of the cargo is contained in the Delivery value object, which is replaced whenever a cargo
 * is handled by an asynchronous event triggered by the registration of the handling event.
 *
 * The delivery can also be affected by routing changes, i.e. when a the route specification
 * changes, or the cargo is assigned to a new route. In that case, the delivery update is performed
 * synchronously within the cargo aggregate.
 *
 * The life cycle of a cargo ends when the cargo is claimed by the customer.
 *
 * The cargo aggregate, and the entre domain model, is built to solve the problem
 * of booking and tracking cargo. All important business rules for determining whether
 * or not a cargo is misdirected, what the current status of the cargo is (on board carrier,
 * in port etc), are captured in this aggregate.
 */
@Entity
public class Cargo extends BaseAggregateRoot<TrackingId> {
    @Id
    @Identity
    private TrackingId trackingId;
    private UnLocode origin;
    private RouteSpecification routeSpecification;
    private Itinerary itinerary;
    private Delivery delivery;

    private Cargo() {
        // required by persistence
    }

    public Cargo(final TrackingId trackingId, final RouteSpecification routeSpecification) {
        Validate.notNull(trackingId, "Route specification is required");
        Validate.notNull(routeSpecification, "Route specification is required");
        // Cargo origin never changes, even if the route specification changes.
        // However, at creation, cargo origin can be derived from the initial route specification.
        this.trackingId = trackingId;
        this.origin = routeSpecification.origin();
        this.routeSpecification = routeSpecification;
        this.delivery = Delivery.derivedFrom(this.routeSpecification, this.itinerary, HandlingHistory.EMPTY);
    }

    /**
     * The tracking id is the identity of this entity, and is unique.
     *
     * @return Tracking id.
     */
    public TrackingId trackingId() {
        return trackingId;
    }

    /**
     * @return Origin location.
     */
    public UnLocode origin() {
        return origin;
    }

    /**
     * @return The delivery. Never null.
     */
    public Delivery delivery() {
        return delivery;
    }

    /**
     * @return The itinerary. Never null.
     */
    public Itinerary itinerary() {
        return this.itinerary == null ? Itinerary.EMPTY_ITINERARY : this.itinerary;
    }

    /**
     * @return The route specification.
     */
    public RouteSpecification routeSpecification() {
        return routeSpecification;
    }

    /**
     * Specifies a new route for this cargo.
     *
     * @param routeSpecification route specification.
     */
    public void specifyNewRoute(final RouteSpecification routeSpecification) {
        Validate.notNull(routeSpecification, "Route specification is required");

        this.routeSpecification = routeSpecification;
        // Handling consistency within the Cargo aggregate synchronously
        this.delivery = delivery.updateOnRouting(this.routeSpecification, this.itinerary);
    }

    /**
     * Attach a new itinerary to this cargo.
     *
     * @param itinerary an itinerary. May not be null.
     */
    public void assignToRoute(final Itinerary itinerary) {
        Validate.notNull(itinerary, "Itinerary is required for assignment");

        this.itinerary = itinerary;
        // Handling consistency within the Cargo aggregate synchronously
        this.delivery = delivery.updateOnRouting(this.routeSpecification, this.itinerary);
    }

    /**
     * Updates all aspects of the cargo aggregate status
     * based on the current route specification, itinerary and handling of the cargo.
     * <p/>
     * When either of those three changes, i.e. when a new route is specified for the cargo,
     * the cargo is assigned to a route or when the cargo is handled, the status must be
     * re-calculated.
     * <p/>
     * {@link RouteSpecification} and {@link Itinerary} are both inside the Cargo
     * aggregate, so changes to them cause the status to be updated <b>synchronously</b>,
     * but changes to the delivery history (when a cargo is handled) cause the status update
     * to happen <b>asynchronously</b> since {@link HandlingEvent} is in a different aggregate.
     *
     * @param handlingHistory handling history
     */
    public void deriveDeliveryProgress(final HandlingHistory handlingHistory) {
        // TODO filter events on cargo (must be same as this cargo)

        // Delivery is a value object, so we can simply discard the old one
        // and replace it with a new
        this.delivery = Delivery.derivedFrom(routeSpecification(), itinerary(), handlingHistory);
    }
}
