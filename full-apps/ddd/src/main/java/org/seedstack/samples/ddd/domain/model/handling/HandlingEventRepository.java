/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.ddd.domain.model.handling;

import java.util.UUID;
import java.util.stream.Collectors;
import org.seedstack.business.domain.Repository;
import org.seedstack.business.specification.Specification;
import org.seedstack.samples.ddd.domain.model.cargo.TrackingId;

/**
 * Handling event repository.
 */
public interface HandlingEventRepository extends Repository<HandlingEvent, UUID> {

    /**
     * @param trackingId cargo tracking id
     * @return The handling history of this cargo
     */
    default HandlingHistory getHandlingHistoryOfCargo(TrackingId trackingId) {
        Specification<HandlingEvent> cargoHandlingEvents = getSpecificationBuilder().of(HandlingEvent.class)
                .property("trackingId.id").equalTo(trackingId.id())
                .build();
        return new HandlingHistory(get(cargoHandlingEvents).collect(Collectors.toList()));
    }
}
