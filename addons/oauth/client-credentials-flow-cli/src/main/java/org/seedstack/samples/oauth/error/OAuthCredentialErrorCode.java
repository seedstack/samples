/*
 * Copyright © 2013-2020, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.samples.oauth.error;

import org.seedstack.shed.exception.ErrorCode;

/**
 * OAuth Credential Flow errors codes
 */
public enum OAuthCredentialErrorCode implements ErrorCode {
    TOKEN_NOT_RECEIVED,
    REQUEST_NOT_SUCCESSFUL
}
