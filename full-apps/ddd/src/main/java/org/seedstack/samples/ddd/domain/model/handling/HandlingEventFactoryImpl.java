/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.ddd.domain.model.handling;

import java.util.Date;
import org.seedstack.business.domain.BaseFactory;
import org.seedstack.samples.ddd.domain.model.cargo.TrackingId;
import org.seedstack.samples.ddd.domain.model.location.UnLocode;
import org.seedstack.samples.ddd.domain.model.voyage.VoyageNumber;

/**
 * Creates handling events.
 */
public class HandlingEventFactoryImpl extends BaseFactory<HandlingEvent> implements HandlingEventFactory {
    /**
     * @param registrationTime time when this event was received by the system
     * @param completionTime   when the event was completed, for example finished loading
     * @param trackingId       cargo tracking id
     * @param voyageNumber     voyage number
     * @param unlocode         United Nations Location Code for the location of the event
     * @param type             type of event
     * @return A handling event.
     * @throws UnknownVoyageException   if there's no voyage with this number
     * @throws UnknownCargoException    if there's no cargo with this tracking id
     * @throws UnknownLocationException if there's no location with this UN Locode
     */
    @Override
    public HandlingEvent createHandlingEvent(Date registrationTime, Date completionTime, TrackingId trackingId,
            VoyageNumber voyageNumber, UnLocode unlocode, HandlingEvent.Type type)
            throws CannotCreateHandlingEventException {
        try {
            if (voyageNumber == null) {
                return new HandlingEvent(trackingId, completionTime, registrationTime, type, unlocode);
            } else {
                return new HandlingEvent(trackingId, completionTime, registrationTime, type, unlocode, voyageNumber);
            }
        } catch (Exception e) {
            throw new CannotCreateHandlingEventException(e);
        }
    }
}
