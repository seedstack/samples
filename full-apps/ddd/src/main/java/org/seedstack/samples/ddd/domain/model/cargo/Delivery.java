/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.ddd.domain.model.cargo;

import static org.seedstack.samples.ddd.domain.model.cargo.RoutingStatus.MISROUTED;
import static org.seedstack.samples.ddd.domain.model.cargo.RoutingStatus.NOT_ROUTED;
import static org.seedstack.samples.ddd.domain.model.cargo.RoutingStatus.ROUTED;
import static org.seedstack.samples.ddd.domain.model.cargo.TransportStatus.CLAIMED;
import static org.seedstack.samples.ddd.domain.model.cargo.TransportStatus.IN_PORT;
import static org.seedstack.samples.ddd.domain.model.cargo.TransportStatus.NOT_RECEIVED;
import static org.seedstack.samples.ddd.domain.model.cargo.TransportStatus.ONBOARD_CARRIER;
import static org.seedstack.samples.ddd.domain.model.cargo.TransportStatus.UNKNOWN;

import java.util.Date;
import java.util.Iterator;
import org.apache.commons.lang.Validate;
import org.mongodb.morphia.annotations.Embedded;
import org.seedstack.business.domain.BaseValueObject;
import org.seedstack.samples.ddd.domain.model.handling.HandlingEvent;
import org.seedstack.samples.ddd.domain.model.handling.HandlingHistory;
import org.seedstack.samples.ddd.domain.model.location.Location;
import org.seedstack.samples.ddd.domain.model.location.UnLocode;
import org.seedstack.samples.ddd.domain.model.voyage.Voyage;
import org.seedstack.samples.ddd.domain.model.voyage.VoyageNumber;

/**
 * The actual transportation of the cargo, as opposed to
 * the customer requirement (RouteSpecification) and the plan (Itinerary).
 */
@Embedded
public class Delivery extends BaseValueObject {
    private static final Date ETA_UNKOWN = null;
    private static final HandlingActivity NO_ACTIVITY = null;
    private TransportStatus transportStatus;
    private UnLocode lastKnownLocation;
    private VoyageNumber currentVoyage;
    private boolean misdirected;
    private Date eta;
    private HandlingActivity nextExpectedActivity;
    private boolean isUnloadedAtDestination;
    private RoutingStatus routingStatus;
    private Date calculatedAt;
    private HandlingEvent lastEvent;

    /**
     * Internal constructor.
     *
     * @param lastEvent          last event
     * @param itinerary          itinerary
     * @param routeSpecification route specification
     */
    private Delivery(HandlingEvent lastEvent, Itinerary itinerary, RouteSpecification routeSpecification) {
        this.calculatedAt = new Date();
        this.lastEvent = lastEvent;

        this.misdirected = calculateMisdirectionStatus(itinerary);
        this.routingStatus = calculateRoutingStatus(itinerary, routeSpecification);
        this.transportStatus = calculateTransportStatus();
        this.lastKnownLocation = calculateLastKnownLocation();
        this.currentVoyage = calculateCurrentVoyage();
        this.eta = calculateEta(itinerary);
        this.nextExpectedActivity = calculateNextExpectedActivity(routeSpecification, itinerary);
        this.isUnloadedAtDestination = calculateUnloadedAtDestination(routeSpecification);
    }

    Delivery() {
        // Needed by Hibernate
    }

    /**
     * Creates a new delivery snapshot based on the complete handling history of a cargo,
     * as well as its route specification and itinerary.
     *
     * @param routeSpecification route specification
     * @param itinerary          itinerary
     * @param handlingHistory    delivery history
     * @return An up to date delivery.
     */
    static Delivery derivedFrom(RouteSpecification routeSpecification, Itinerary itinerary,
            HandlingHistory handlingHistory) {
        Validate.notNull(routeSpecification, "Route specification is required");
        Validate.notNull(handlingHistory, "Delivery history is required");

        final HandlingEvent lastEvent = handlingHistory.mostRecentlyCompletedEvent();

        return new Delivery(lastEvent, itinerary, routeSpecification);
    }

    /**
     * Creates a new delivery snapshot to reflect changes in routing, i.e.
     * when the route specification or the itinerary has changed
     * but no additional handling of the cargo has been performed.
     *
     * @param routeSpecification route specification
     * @param itinerary          itinerary
     * @return An up to date delivery
     */
    Delivery updateOnRouting(RouteSpecification routeSpecification, Itinerary itinerary) {
        Validate.notNull(routeSpecification, "Route specification is required");

        return new Delivery(this.lastEvent, itinerary, routeSpecification);
    }

    /**
     * @return Transport status
     */
    public TransportStatus transportStatus() {
        return transportStatus;
    }

    /**
     * @return Last known location of the cargo, or Location.UNKNOWN if the delivery history is empty.
     */
    public UnLocode lastKnownLocation() {
        return lastKnownLocation == null ? Location.UNKNOWN.code() : lastKnownLocation;
    }

    /**
     * @return Current voyage.
     */
    public VoyageNumber currentVoyage() {
        return currentVoyage == null ? Voyage.NONE.number() : currentVoyage;
    }

    /**
     * Check if cargo is misdirected.
     * <p/>
     * <ul>
     * <li>A cargo is misdirected if it is in a location that's not in the itinerary.
     * <li>A cargo with no itinerary can not be misdirected.
     * <li>A cargo that has received no handling events can not be misdirected.
     * </ul>
     *
     * @return <code>true</code> if the cargo has been misdirected,
     */
    public boolean isMisdirected() {
        return misdirected;
    }

    /**
     * @return Estimated time of arrival
     */
    public Date estimatedTimeOfArrival() {
        if (eta != ETA_UNKOWN) {
            return new Date(eta.getTime());
        } else {
            return ETA_UNKOWN;
        }
    }

    /**
     * @return The next expected handling activity.
     */
    public HandlingActivity nextExpectedActivity() {
        return nextExpectedActivity;
    }

    /**
     * @return True if the cargo has been unloaded at the final destination.
     */
    public boolean isUnloadedAtDestination() {
        return isUnloadedAtDestination;
    }

    /**
     * @return Routing status.
     */
    public RoutingStatus routingStatus() {
        return routingStatus;
    }

    // TODO add currentCarrierMovement (?)

    // --- Internal calculations below ---

    /**
     * @return When this delivery was calculated.
     */
    public Date calculatedAt() {
        return new Date(calculatedAt.getTime());
    }

    private TransportStatus calculateTransportStatus() {
        if (lastEvent == null) {
            return NOT_RECEIVED;
        }

        switch (lastEvent.type()) {
            case LOAD:
                return ONBOARD_CARRIER;
            case UNLOAD:
            case RECEIVE:
            case CUSTOMS:
                return IN_PORT;
            case CLAIM:
                return CLAIMED;
            default:
                return UNKNOWN;
        }
    }

    private UnLocode calculateLastKnownLocation() {
        if (lastEvent != null) {
            return lastEvent.location();
        } else {
            return null;
        }
    }

    private VoyageNumber calculateCurrentVoyage() {
        if (transportStatus().equals(ONBOARD_CARRIER) && lastEvent != null) {
            return lastEvent.voyage();
        } else {
            return null;
        }
    }

    private boolean calculateMisdirectionStatus(Itinerary itinerary) {
        if (lastEvent == null) {
            return false;
        } else {
            return !itinerary.isExpected(lastEvent);
        }
    }

    private Date calculateEta(Itinerary itinerary) {
        if (onTrack()) {
            return itinerary.finalArrivalDate();
        } else {
            return ETA_UNKOWN;
        }
    }

    private HandlingActivity calculateNextExpectedActivity(RouteSpecification routeSpecification, Itinerary itinerary) {
        if (!onTrack()) return NO_ACTIVITY;

        if (lastEvent == null) return new HandlingActivity(HandlingEvent.Type.RECEIVE, routeSpecification.origin());

        switch (lastEvent.type()) {

            case LOAD:
                for (Leg leg : itinerary.legs()) {
                    if (leg.loadLocation().equals(lastEvent.location())) {
                        return new HandlingActivity(HandlingEvent.Type.UNLOAD, leg.unloadLocation(), leg.voyage());
                    }
                }

                return NO_ACTIVITY;

            case UNLOAD:
                for (Iterator<Leg> it = itinerary.legs().iterator(); it.hasNext(); ) {
                    final Leg leg = it.next();
                    if (leg.unloadLocation().equals(lastEvent.location())) {
                        if (it.hasNext()) {
                            final Leg nextLeg = it.next();
                            return new HandlingActivity(HandlingEvent.Type.LOAD,
                                    nextLeg.loadLocation(),
                                    nextLeg.voyage());
                        } else {
                            return new HandlingActivity(HandlingEvent.Type.CLAIM, leg.unloadLocation());
                        }
                    }
                }

                return NO_ACTIVITY;

            case RECEIVE:
                final Leg firstLeg = itinerary.legs().iterator().next();
                return new HandlingActivity(HandlingEvent.Type.LOAD, firstLeg.loadLocation(), firstLeg.voyage());

            case CLAIM:
            default:
                return NO_ACTIVITY;
        }
    }

    private RoutingStatus calculateRoutingStatus(Itinerary itinerary, RouteSpecification routeSpecification) {
        if (itinerary == null) {
            return NOT_ROUTED;
        } else {
            if (routeSpecification.isSatisfiedBy(itinerary)) {
                return ROUTED;
            } else {
                return MISROUTED;
            }
        }
    }

    private boolean calculateUnloadedAtDestination(RouteSpecification routeSpecification) {
        return lastEvent != null &&
                HandlingEvent.Type.UNLOAD.equals(lastEvent.type()) &&
                routeSpecification.destination().equals(lastEvent.location());
    }

    private boolean onTrack() {
        return routingStatus.equals(ROUTED) && !misdirected;
    }
}
