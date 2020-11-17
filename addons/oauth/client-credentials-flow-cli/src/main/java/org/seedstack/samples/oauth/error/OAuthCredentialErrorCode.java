package org.seedstack.samples.oauth.error;

import org.seedstack.shed.exception.ErrorCode;

/**
 * OAuth Credential Flow errors codes
 */
public enum OAuthCredentialErrorCode implements ErrorCode {
    TOKEN_NOT_RECEIVED,
    REQUEST_NOT_SUCCESSFUL
}
