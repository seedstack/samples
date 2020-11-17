/*
 * Copyright © 2013-2020, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.samples.oauth;

import org.apache.shiro.authz.UnauthenticatedException;
import org.seedstack.seed.security.RequiresPermissions;
import org.seedstack.seed.security.SecuritySupport;
import org.seedstack.seed.security.principals.PrincipalProvider;
import org.seedstack.seed.security.principals.Principals;
import org.seedstack.seed.security.principals.SimplePrincipalProvider;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Path("/profile")
public class ProfileResource {
    @Inject
    private SecuritySupport securitySupport;

    @GET
    @Produces("application/json")
    @RequiresPermissions("CodeFlowScope")
    public ProfileRepresentation sayHello() {
        ProfileRepresentation profileRepresentation = new ProfileRepresentation();
        profileRepresentation.setUserId(Optional.ofNullable(securitySupport.getIdentityPrincipal())
                .map(PrincipalProvider::get)
                .map(Object::toString)
                .orElseThrow(() -> new UnauthenticatedException("No user identity available")));
        Optional.ofNullable(securitySupport.getSimplePrincipalByName(Principals.FIRST_NAME))
                .map(SimplePrincipalProvider::get)
                .ifPresent(profileRepresentation::setFirstName);
        Optional.ofNullable(securitySupport.getSimplePrincipalByName(Principals.LAST_NAME))
                .map(SimplePrincipalProvider::get)
                .ifPresent(profileRepresentation::setLastName);
        Optional.ofNullable(securitySupport.getSimplePrincipalByName(Principals.FULL_NAME))
                .map(SimplePrincipalProvider::get)
                .ifPresent(profileRepresentation::setFullName);
        Optional.ofNullable(securitySupport.getSimplePrincipalByName("picture"))
                .map(SimplePrincipalProvider::get)
                .ifPresent(profileRepresentation::setPictureUrl);

        Map<String, String> principals = new HashMap<>();
        for (SimplePrincipalProvider simplePrincipalProvider : securitySupport.getSimplePrincipals()) {
            principals.put(simplePrincipalProvider.getName(), simplePrincipalProvider.getValue());
        }
        profileRepresentation.setPrincipals(principals);

        return profileRepresentation;
    }
}
