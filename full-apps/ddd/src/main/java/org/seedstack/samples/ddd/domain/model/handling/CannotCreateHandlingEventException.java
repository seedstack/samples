/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.ddd.domain.model.handling;

/**
 * If a {@link org.seedstack.samples.ddd.domain.model.handling.HandlingEvent} can't be
 * created from a given set of parameters.
 *
 * It is a checked exception because it's not a programming error, but rather a
 * special case that the application is built to handle. It can occur during normal
 * program execution.
 */
public class CannotCreateHandlingEventException extends Exception {
    public CannotCreateHandlingEventException(Exception e) {
        super(e);
    }

    public CannotCreateHandlingEventException() {
        super();
    }
}
