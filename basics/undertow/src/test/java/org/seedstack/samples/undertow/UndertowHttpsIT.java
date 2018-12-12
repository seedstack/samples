/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.samples.undertow;

import static org.assertj.core.api.Assertions.assertThat;

import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seedstack.seed.Configuration;
import org.seedstack.seed.testing.junit4.SeedITRunner;
import org.seedstack.seed.undertow.LaunchWithUndertow;

@RunWith(SeedITRunner.class)
@LaunchWithUndertow
public class UndertowHttpsIT {
    @Configuration("runtime.web.baseUrl")
    private String baseUrl;

    @Test
    public void testHelloWorld() throws Exception {
        Response response = RestAssured.given()
                .config(RestAssured.config().sslConfig(SSLConfig.sslConfig().relaxedHTTPSValidation("SSL")))
                .expect()
                .statusCode(200)
                .when()
                .get(baseUrl);
        assertThat(response.body().asString()).isEqualTo("Hello World!");
    }
}
