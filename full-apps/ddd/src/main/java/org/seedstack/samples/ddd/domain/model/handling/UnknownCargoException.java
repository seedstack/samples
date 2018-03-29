/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.ddd.domain.model.handling;

import org.seedstack.samples.ddd.domain.model.cargo.TrackingId;

/**
 * Thrown when trying to register an event with an unknown tracking id.
 */
public final class UnknownCargoException extends CannotCreateHandlingEventException {

    private final TrackingId trackingId;

    /**
     * @param trackingId cargo tracking id
     */
    public UnknownCargoException(final TrackingId trackingId) {
        this.trackingId = trackingId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMessage() {
        return "No cargo with tracking id " + trackingId.id() + " exists in the system";
    }
}
