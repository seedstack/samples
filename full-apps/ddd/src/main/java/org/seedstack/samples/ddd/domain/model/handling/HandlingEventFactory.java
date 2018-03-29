/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.ddd.domain.model.handling;

import java.util.Date;
import org.seedstack.business.domain.Factory;
import org.seedstack.samples.ddd.domain.model.cargo.TrackingId;
import org.seedstack.samples.ddd.domain.model.location.UnLocode;
import org.seedstack.samples.ddd.domain.model.voyage.VoyageNumber;

public interface HandlingEventFactory extends Factory<HandlingEvent> {
    HandlingEvent createHandlingEvent(Date registrationTime, Date completionTime, TrackingId trackingId,
            VoyageNumber voyageNumber, UnLocode unlocode, HandlingEvent.Type type)
            throws CannotCreateHandlingEventException;
}
