/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.ddd.application.services;

import java.util.Date;
import org.seedstack.business.Service;
import org.seedstack.samples.ddd.domain.model.cargo.TrackingId;
import org.seedstack.samples.ddd.domain.model.handling.CannotCreateHandlingEventException;
import org.seedstack.samples.ddd.domain.model.handling.HandlingEvent;
import org.seedstack.samples.ddd.domain.model.location.UnLocode;
import org.seedstack.samples.ddd.domain.model.voyage.VoyageNumber;

/**
 * Handling event service.
 */
@Service
public interface HandlingEventService {

    /**
     * Registers a handling event in the system, and notifies interested
     * parties that a cargo has been handled.
     *
     * @param completionTime when the event was completed
     * @param trackingId     cargo tracking id
     * @param voyageNumber   voyage number
     * @param unLocode       UN locode for the location where the event occurred
     * @param type           type of event
     * @throws CannotCreateHandlingEventException if a handling event that represents an actual event that's relevant
     *                                            to a cargo we're tracking can't be created from the parameters.
     */
    void registerHandlingEvent(Date completionTime,
            TrackingId trackingId,
            VoyageNumber voyageNumber,
            UnLocode unLocode,
            HandlingEvent.Type type) throws CannotCreateHandlingEventException;

}
