/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.ddd.domain.model.voyage;

import java.util.Date;
import org.apache.commons.lang.Validate;
import org.mongodb.morphia.annotations.Embedded;
import org.seedstack.business.domain.BaseValueObject;
import org.seedstack.samples.ddd.domain.model.location.Location;
import org.seedstack.samples.ddd.domain.model.location.UnLocode;

/**
 * A carrier movement is a vessel voyage from one location to another.
 */
@Embedded
public final class CarrierMovement extends BaseValueObject {
    public static final CarrierMovement NONE = new CarrierMovement(
            Location.UNKNOWN.code(), Location.UNKNOWN.code(),
            new Date(0), new Date(0)
    );
    private UnLocode departureLocation;
    private UnLocode arrivalLocation;
    private Date departureTime;
    private Date arrivalTime;

    private CarrierMovement() {
        // required by persistence
    }

    /**
     * Constructor.
     *
     * @param departureLocation location of departure
     * @param arrivalLocation   location of arrival
     * @param departureTime     time of departure
     * @param arrivalTime       time of arrival
     */
    // TODO make package local
    public CarrierMovement(UnLocode departureLocation,
            UnLocode arrivalLocation,
            Date departureTime,
            Date arrivalTime) {
        Validate.noNullElements(new Object[]{departureLocation, arrivalLocation, departureTime, arrivalTime});
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.departureLocation = departureLocation;
        this.arrivalLocation = arrivalLocation;
    }

    /**
     * @return Departure location.
     */
    public UnLocode departureLocation() {
        return departureLocation;
    }

    /**
     * @return Arrival location.
     */
    public UnLocode arrivalLocation() {
        return arrivalLocation;
    }

    /**
     * @return Time of departure.
     */
    public Date departureTime() {
        return new Date(departureTime.getTime());
    }

    /**
     * @return Time of arrival.
     */
    public Date arrivalTime() {
        return new Date(arrivalTime.getTime());
    }

}
