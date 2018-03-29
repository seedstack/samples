/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.ddd.application.services;

import java.util.Date;
import java.util.List;
import org.seedstack.business.Service;
import org.seedstack.samples.ddd.domain.model.cargo.Itinerary;
import org.seedstack.samples.ddd.domain.model.cargo.TrackingId;
import org.seedstack.samples.ddd.domain.model.location.UnLocode;

/**
 * Cargo booking service.
 */
@Service
public interface BookingService {

    /**
     * Registers a new cargo in the tracking system, not yet routed.
     *
     * @param origin          cargo origin
     * @param destination     cargo destination
     * @param arrivalDeadline arrival deadline
     * @return Cargo tracking id
     */
    TrackingId bookNewCargo(UnLocode origin, UnLocode destination, Date arrivalDeadline);

    /**
     * Requests a list of itineraries describing possible routes for this cargo.
     *
     * @param trackingId cargo tracking id
     * @return A list of possible itineraries for this cargo
     */
    List<Itinerary> requestPossibleRoutesForCargo(TrackingId trackingId);

    /**
     * @param itinerary  itinerary describing the selected route
     * @param trackingId cargo tracking id
     */
    void assignCargoToRoute(Itinerary itinerary, TrackingId trackingId);

    /**
     * Changes the destination of a cargo.
     *
     * @param trackingId cargo tracking id
     * @param unLocode   UN locode of new destination
     */
    void changeDestination(TrackingId trackingId, UnLocode unLocode);

}
