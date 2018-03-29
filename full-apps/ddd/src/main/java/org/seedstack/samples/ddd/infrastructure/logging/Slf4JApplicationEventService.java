/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.ddd.infrastructure.logging;

import org.seedstack.samples.ddd.application.services.ApplicationEventService;
import org.seedstack.samples.ddd.domain.model.cargo.Cargo;
import org.seedstack.samples.ddd.domain.model.handling.HandlingEvent;
import org.seedstack.seed.Logging;
import org.slf4j.Logger;

public class Slf4JApplicationEventService implements ApplicationEventService {
    @Logging
    private Logger logger;

    @Override
    public void cargoWasHandled(HandlingEvent event) {
        logger.info("Cargo was handled: {}", event);
    }

    @Override
    public void cargoWasMisdirected(Cargo cargo) {
        logger.info("Cargo was mis-directed: {}", cargo);

    }

    @Override
    public void cargoHasArrived(Cargo cargo) {
        logger.info("Cargo has arrived: {}", cargo);
    }
}
