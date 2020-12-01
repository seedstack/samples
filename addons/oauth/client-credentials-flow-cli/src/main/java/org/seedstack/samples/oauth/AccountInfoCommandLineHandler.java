/*
 * Copyright © 2013-2020, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.samples.oauth;

import org.seedstack.oauth.OAuthAuthenticationToken;
import org.seedstack.oauth.OAuthService;
import org.seedstack.samples.oauth.api.AccountInfo;
import org.seedstack.samples.oauth.api.SecuredApi;
import org.seedstack.samples.oauth.error.OAuthCredentialErrorCode;
import org.seedstack.samples.oauth.error.OAuthSampleException;
import org.seedstack.seed.Logging;
import org.seedstack.seed.SeedException;
import org.seedstack.seed.cli.CliCommand;
import org.seedstack.seed.cli.CommandLineHandler;
import org.slf4j.Logger;

import javax.inject.Inject;

@CliCommand("accountInfo")
public class AccountInfoCommandLineHandler implements CommandLineHandler {

    @Logging
    private Logger logger;

    @Inject
    private OAuthService oAuthService;

    @Inject
    private SecuredApi securedApi;

    @Override
    public Integer call() throws Exception {
        logger.info("OAuth samples - Client credential flow - Client started");

        //First step - Request for authentication token
        OAuthAuthenticationToken token = requestAuthenticationToken();

        //Step 2 - Request secured api with bearer token
        String accountId = callSecuredApi(token).getAccountId();

        logger.info("Secured call response : {}", accountId);
        return 0;
    }

    /**
     * Request the Authentication token from the Auth server
     * Uses the Oauth configuration, more more information to set here.
     *
     * @return OAuthAuthenticationToken Authentication token
     */
    private OAuthAuthenticationToken requestAuthenticationToken() {
        logger.info("Requesting authentication token.");
        OAuthAuthenticationToken token = oAuthService.requestTokensWithClientCredentials();
        if (token == null) {
            throw SeedException.wrap(new OAuthSampleException("Authentication token is null"), OAuthCredentialErrorCode.TOKEN_NOT_RECEIVED);
        }
        logger.info("Authentication token received.");
        return token;
    }

    /**
     * Call the secured API with an authentication token
     *
     * @param token The authentication token previously requested from Auth server
     * @return AccountInfo the account information response
     */
    private AccountInfo callSecuredApi(OAuthAuthenticationToken token) {
        logger.info("Calling secured web service");
        return securedApi.accountInfo(token.getAccessToken());
    }
}