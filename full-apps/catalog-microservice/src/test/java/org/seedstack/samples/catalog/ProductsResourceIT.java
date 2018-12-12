/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.catalog;

import static io.restassured.RestAssured.expect;

import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seedstack.seed.Configuration;
import org.seedstack.seed.testing.junit4.internal.JUnit4Runner;
import org.seedstack.seed.undertow.LaunchWithUndertow;
import org.skyscreamer.jsonassert.JSONAssert;

@RunWith(JUnit4Runner.class)
@LaunchWithUndertow
public class ProductsResourceIT {
    @Configuration("runtime.web.baseUrl")
    private String baseUrl;

    @Test
    public void hal_builder() throws JSONException {
        Response response = expect().statusCode(200).given().header("Content-Type", "application/hal+json")
                .get(baseUrl + "/products?pageSize=10");

        JSONObject expectedResponse = expectedResponse();

        JSONAssert.assertEquals(expectedResponse, new JSONObject(response.asString()), false);
    }

    private JSONObject expectedResponse() throws JSONException {
        JSONObject obj = new JSONObject();
        // FIXME links no more include query parameters due to the missing support for BeanParam in HAL
        // uncomment when the support will be added
        //expectedLinks(obj);

        JSONObject embedded = new JSONObject();
        JSONArray products = new JSONArray();
        for (int i = 0; i < 10; i++) {
            products.put(new JSONObject());
        }
        embedded.put("products", products);
        obj.put("_embedded", embedded);
        return obj;
    }

    @Test
    public void json_home() {
        Response response = expect().statusCode(200).given().header("Content-Type", "application/json-home")
                .get(baseUrl);

        Assertions.assertThat(response.asString()).isNotEmpty();
    }

    @Test
    public void validate_pagination() {
        expect().statusCode(400).given().header("Content-Type", "application/hal+json")
                .get(baseUrl + "/products?pageSize=0");

        expect().statusCode(400).given().header("Content-Type", "application/hal+json")
                .get(baseUrl + "/products?pageIndex=-1");
    }

    private void expectedLinks(JSONObject obj) throws JSONException {
        JSONObject links = new JSONObject();
        links.put("self", new JSONObject().put("href", "/products?pageIndex=0&pageSize=10"));
        links.put("next", new JSONObject().put("href", "/products?pageIndex=1&pageSize=10"));
        obj.put("_links", links);
    }
}
