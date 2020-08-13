/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.ddd.domain.model.cargo;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

import java.util.Date;

import dev.morphia.annotations.Embedded;
import org.seedstack.business.domain.BaseValueObject;
import org.seedstack.business.specification.Specification;
import org.seedstack.samples.ddd.domain.model.location.UnLocode;

/**
 * Route specification. Describes where a cargo orign and destination is,
 * and the arrival deadline.
 */
@Embedded
public class RouteSpecification extends BaseValueObject implements Specification<Itinerary> {
    private UnLocode origin;
    private UnLocode destination;
    private Date arrivalDeadline;

    private RouteSpecification() {
        // required by persistence
    }

    /**
     * @param origin          origin location - can't be the same as the destination
     * @param destination     destination location - can't be the same as the origin
     * @param arrivalDeadline arrival deadline
     */
    public RouteSpecification(final UnLocode origin, final UnLocode destination, final Date arrivalDeadline) {
        checkNotNull(origin, "Origin is required");
        checkNotNull(destination, "Destination is required");
        checkNotNull(arrivalDeadline, "Arrival deadline is required");
        checkState(!origin.equals(destination), "Origin and destination can't be the same: " + origin);

        this.origin = origin;
        this.destination = destination;
        this.arrivalDeadline = (Date) arrivalDeadline.clone();
    }

    /**
     * @return Specified origin location.
     */
    public UnLocode origin() {
        return origin;
    }

    /**
     * @return Specfied destination location.
     */
    public UnLocode destination() {
        return destination;
    }

    /**
     * @return Arrival deadline.
     */
    public Date arrivalDeadline() {
        return new Date(arrivalDeadline.getTime());
    }

    @Override
    public boolean isSatisfiedBy(final Itinerary itinerary) {
        return itinerary != null &&
                origin().equals(itinerary.initialDepartureLocation()) &&
                destination().equals(itinerary.finalArrivalLocation()) &&
                arrivalDeadline().after(itinerary.finalArrivalDate());
    }

}
