/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.ddd.domain.model.handling;

import org.seedstack.samples.ddd.domain.model.location.UnLocode;

public class UnknownLocationException extends CannotCreateHandlingEventException {

    private final UnLocode unlocode;

    public UnknownLocationException(final UnLocode unlocode) {
        this.unlocode = unlocode;
    }

    @Override
    public String getMessage() {
        return "No location with UN locode " + unlocode.idString() + " exists in the system";
    }
}
