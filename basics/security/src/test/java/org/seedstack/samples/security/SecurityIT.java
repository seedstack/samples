/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.security;

import javax.inject.Inject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seedstack.seed.Configuration;
import org.seedstack.seed.security.AuthorizationException;
import org.seedstack.seed.security.RequiresPermissions;
import org.seedstack.seed.security.SecuritySupport;
import org.seedstack.seed.security.WithUser;
import org.seedstack.seed.testing.junit4.internal.JUnit4Runner;

@RunWith(JUnit4Runner.class)
public class SecurityIT {
    @Configuration("myApp.country")
    private String country;
    @Inject
    private SecuritySupport securitySupport;

    @Test
    @WithUser(id = "alice", password = "password")
    public void testAliceCanDeleteUser() {
        deleteUser("someone");
    }

    @Test(expected = AuthorizationException.class)
    @WithUser(id = "bob", password = "password")
    public void testBobCannotDeleteUser() {
        deleteUser("someone");
    }

    @Test
    @WithUser(id = "carol", password = "password")
    public void testCarolCanEditArticle() {
        editArticle("someArticle");
    }

    @Test(expected = AuthorizationException.class)
    @WithUser(id = "dave", password = "password")
    public void testDaveCannotEditArticle() {
        editArticle("someArticle");
    }

    public void deleteUser(String userId) {
        if (!securitySupport.isPermitted("user:delete", new Location(country))) {
            throw new AuthorizationException("An administrator role for country " + country + " is required to delete" +
                    " a user");
        }
        System.out.println("Deleting user " + userId);
    }

    @RequiresPermissions("article:edit")
    public void editArticle(String articleId) {
        System.out.println("Editing article " + articleId);
    }
}
