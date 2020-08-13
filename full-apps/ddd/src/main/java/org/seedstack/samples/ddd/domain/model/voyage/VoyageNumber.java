/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.ddd.domain.model.voyage;

import static com.google.common.base.Preconditions.checkNotNull;

import dev.morphia.annotations.Embedded;
import org.seedstack.business.domain.BaseValueObject;

/**
 * Identifies a voyage.
 */
@Embedded
public class VoyageNumber extends BaseValueObject {
    private String number;

    private VoyageNumber() {
        // required by persistence
    }

    public VoyageNumber(String number) {
        this.number = checkNotNull(number);
    }

    public String idString() {
        return number;
    }

}
