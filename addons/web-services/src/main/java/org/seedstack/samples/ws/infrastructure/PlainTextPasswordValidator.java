/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.ws.infrastructure;

import com.sun.xml.wss.impl.callback.PasswordValidationCallback;

/**
 * A sample basic password validator.
 */
public class PlainTextPasswordValidator implements PasswordValidationCallback.PasswordValidator {
    @Override
    public boolean validate(
            PasswordValidationCallback.Request request) throws PasswordValidationCallback.PasswordValidationException {
        PasswordValidationCallback.PlainTextPasswordRequest plainTextRequest = (PasswordValidationCallback
                .PlainTextPasswordRequest) request;
        return "demo".equals(plainTextRequest.getUsername()) && "demo".equals(plainTextRequest.getPassword());
    }
}
