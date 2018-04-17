/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.web;

import static io.restassured.RestAssured.expect;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seedstack.seed.Configuration;
import org.seedstack.seed.testing.junit4.internal.JUnit4Runner;
import org.seedstack.seed.undertow.LaunchWithUndertow;

@RunWith(JUnit4Runner.class)
@LaunchWithUndertow
public class WebIT {
    @Configuration("web.runtime.baseUrl")
    private String baseUrl;

    @Test
    public void testServlet() {
        expect()
                .statusCode(200)
                .body(Matchers.equalTo("Hello World from web-sample!"))
                .when()
                .get(baseUrl + "my-servlet?name=World");
    }
}
