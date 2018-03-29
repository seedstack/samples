/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.ddd.application.services.impl;

import java.util.Date;
import javax.inject.Inject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.seedstack.samples.ddd.application.services.ApplicationEventService;
import org.seedstack.samples.ddd.application.services.HandlingEventService;
import org.seedstack.samples.ddd.domain.model.cargo.TrackingId;
import org.seedstack.samples.ddd.domain.model.handling.CannotCreateHandlingEventException;
import org.seedstack.samples.ddd.domain.model.handling.HandlingEvent;
import org.seedstack.samples.ddd.domain.model.handling.HandlingEventFactory;
import org.seedstack.samples.ddd.domain.model.handling.HandlingEventRepository;
import org.seedstack.samples.ddd.domain.model.location.UnLocode;
import org.seedstack.samples.ddd.domain.model.voyage.VoyageNumber;
import org.seedstack.seed.transaction.Transactional;

public class HandlingEventServiceImpl implements HandlingEventService {
    private final ApplicationEventService applicationEventService;
    private final HandlingEventRepository handlingEventRepository;
    private final HandlingEventFactory handlingEventFactory;
    private final Log logger = LogFactory.getLog(HandlingEventServiceImpl.class);

    @Inject
    public HandlingEventServiceImpl(final HandlingEventRepository handlingEventRepository,
            final ApplicationEventService applicationEventService,
            final HandlingEventFactory handlingEventFactory) {
        this.handlingEventRepository = handlingEventRepository;
        this.applicationEventService = applicationEventService;
        this.handlingEventFactory = handlingEventFactory;
    }

    @Override
    @Transactional(rollbackOn = CannotCreateHandlingEventException.class)
    public void registerHandlingEvent(final Date completionTime,
            final TrackingId trackingId,
            final VoyageNumber voyageNumber,
            final UnLocode unLocode,
            final HandlingEvent.Type type) throws CannotCreateHandlingEventException {
        final Date registrationTime = new Date();
    /* Using a factory to create a HandlingEvent (aggregate). This is where
       it is determined wether the incoming data, the attempt, actually is capable
       of representing a real handling event. */
        final HandlingEvent event = handlingEventFactory.createHandlingEvent(
                registrationTime, completionTime, trackingId, voyageNumber, unLocode, type
        );

    /* Store the new handling event, which updates the persistent
       state of the handling event aggregate (but not the cargo aggregate -
       that happens asynchronously!)
     */
        handlingEventRepository.add(event);

        /* Publish an event stating that a cargo has been handled. */
        applicationEventService.cargoWasHandled(event);

        logger.info("Registered handling event");
    }
}
