/*
 * Copyright © 2013-2020, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.samples.oauth;

import org.seedstack.oauth.spi.OAuthAuthenticationToken;
import org.seedstack.oauth.spi.OAuthService;
import org.seedstack.seed.cli.CliCommand;
import org.seedstack.seed.cli.CommandLineHandler;
import org.seedstack.seed.security.SecuritySupport;
import org.seedstack.seed.security.principals.Principals;

import javax.inject.Inject;

@CliCommand("userinfo")
public class UserInfoCommandLineHandler implements CommandLineHandler {
    @Inject
    private OAuthService oAuthService;
    @Inject
    private SecuritySupport securitySupport;

    @Override
    public Integer call() {
        OAuthAuthenticationToken token = oAuthService.requestTokensWithClientCredentials();
        securitySupport.login(token);

        System.out.printf("User full name: %s\n", securitySupport.getSimplePrincipalByName(Principals.FULL_NAME));

        securitySupport.logout();
        return 0;
    }
}
