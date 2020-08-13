/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.ddd.application.services.impl;

import java.util.Optional;
import javax.inject.Inject;

import org.apache.commons.lang3.Validate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.seedstack.business.domain.Repository;
import org.seedstack.samples.ddd.application.services.ApplicationEventService;
import org.seedstack.samples.ddd.application.services.CargoInspectionService;
import org.seedstack.samples.ddd.domain.model.cargo.Cargo;
import org.seedstack.samples.ddd.domain.model.cargo.TrackingId;
import org.seedstack.samples.ddd.domain.model.handling.HandlingEventRepository;
import org.seedstack.samples.ddd.domain.model.handling.HandlingHistory;
import org.seedstack.seed.transaction.Transactional;

public class CargoInspectionServiceImpl implements CargoInspectionService {
    private final ApplicationEventService applicationEventService;
    private final Repository<Cargo, TrackingId> cargoRepository;
    private final HandlingEventRepository handlingEventRepository;
    private final Log logger = LogFactory.getLog(getClass());

    @Inject
    public CargoInspectionServiceImpl(final ApplicationEventService applicationEventService,
            final Repository<Cargo, TrackingId> cargoRepository,
            final HandlingEventRepository handlingEventRepository) {
        this.applicationEventService = applicationEventService;
        this.cargoRepository = cargoRepository;
        this.handlingEventRepository = handlingEventRepository;
    }

    @Override
    @Transactional
    public void inspectCargo(final TrackingId trackingId) {
        Validate.notNull(trackingId, "Tracking ID is required");

        final Optional<Cargo> cargoOptional = cargoRepository.get(trackingId);
        if (cargoOptional.isPresent()) {
            final Cargo cargo = cargoOptional.get();
            final HandlingHistory handlingHistory = handlingEventRepository.getHandlingHistoryOfCargo(trackingId);

            cargo.deriveDeliveryProgress(handlingHistory);

            if (cargo.delivery().isMisdirected()) {
                applicationEventService.cargoWasMisdirected(cargo);
            }

            if (cargo.delivery().isUnloadedAtDestination()) {
                applicationEventService.cargoHasArrived(cargo);
            }

            cargoRepository.add(cargo);
        } else {
            logger.warn("Can't inspect non-existing cargo " + trackingId);
        }
    }
}
