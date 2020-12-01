/*
 * Copyright © 2013-2020, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.samples.oauth;

import org.seedstack.seed.Logging;
import org.seedstack.seed.security.RequiresPermissions;
import org.seedstack.seed.security.SecuritySupport;
import org.seedstack.seed.security.principals.Principals;
import org.seedstack.seed.security.principals.SimplePrincipalProvider;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.Optional;

/**
 * Secured api for account information
 * security support is available, all interactions with Authentication server has
 * already been made by the framework
 */
@Path("/accountInfo")
public class AccountInfoResource {

    /**Security information*/
    @Inject
    private SecuritySupport securitySupport;

    @Logging
    private Logger logger;

    /**
     * The secured end point.
     * Accessing this endpoint requires the "profile" permission
     * At this point, security information such as Principals are known
     * @return AccountInfoRepresentation
     */
    @GET
    @Produces("application/json")
    @RequiresPermissions("profile")
    public AccountInfoRepresentation getInfos(){
        logger.info("Request received and accepted.");
        AccountInfoRepresentation representation = new AccountInfoRepresentation();
        Optional.ofNullable(securitySupport.getSimplePrincipalByName(Principals.IDENTITY))
                .map(SimplePrincipalProvider::get)
                .ifPresent(representation::setAccountId);
        return representation;
    }
}
