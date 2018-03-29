/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.ddd.domain.model.voyage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang.Validate;
import org.mongodb.morphia.annotations.Embedded;
import org.seedstack.business.domain.BaseValueObject;

/**
 * A voyage schedule.
 */
@Embedded
public class Schedule extends BaseValueObject {
    public static final Schedule EMPTY = new Schedule();
    private List<CarrierMovement> carrierMovements = new ArrayList<>();

    private Schedule() {
        // required by persistence
    }

    public Schedule(final List<CarrierMovement> carrierMovements) {
        Validate.notNull(carrierMovements);
        Validate.noNullElements(carrierMovements);
        Validate.notEmpty(carrierMovements);

        this.carrierMovements = carrierMovements;
    }

    /**
     * @return Carrier movements.
     */
    public List<CarrierMovement> carrierMovements() {
        return Collections.unmodifiableList(carrierMovements);
    }

}
