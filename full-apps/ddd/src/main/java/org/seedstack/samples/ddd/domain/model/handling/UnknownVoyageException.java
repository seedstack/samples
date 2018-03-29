/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.ddd.domain.model.handling;

import org.seedstack.samples.ddd.domain.model.voyage.VoyageNumber;

/**
 * Thrown when trying to register an event with an unknown carrier movement id.
 */
public class UnknownVoyageException extends CannotCreateHandlingEventException {

    private final VoyageNumber voyageNumber;

    public UnknownVoyageException(VoyageNumber voyageNumber) {
        this.voyageNumber = voyageNumber;
    }

    @Override
    public String getMessage() {
        return "No voyage with number " + voyageNumber.idString() + " exists in the system";
    }
}
