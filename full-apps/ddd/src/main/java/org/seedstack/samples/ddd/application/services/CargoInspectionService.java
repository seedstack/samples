/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.ddd.application.services;

import org.seedstack.business.Service;
import org.seedstack.samples.ddd.domain.model.cargo.TrackingId;

/**
 * Cargo inspection service.
 */
@Service
public interface CargoInspectionService {

    /**
     * Inspect cargo and send relevant notifications to interested parties,
     * for example if a cargo has been misdirected, or unloaded
     * at the final destination.
     *
     * @param trackingId cargo tracking id
     */
    void inspectCargo(TrackingId trackingId);

}
