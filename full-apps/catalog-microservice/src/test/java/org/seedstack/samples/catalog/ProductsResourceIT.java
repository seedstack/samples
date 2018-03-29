/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.catalog;

import static com.jayway.restassured.RestAssured.expect;

import com.jayway.restassured.response.Response;
import java.net.URL;
import org.assertj.core.api.Assertions;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.seedstack.seed.it.AbstractSeedWebIT;
import org.skyscreamer.jsonassert.JSONAssert;

public class ProductsResourceIT extends AbstractSeedWebIT {
    @ArquillianResource
    private URL baseURL;

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class);
    }

    @RunAsClient
    @Test
    public void hal_builder() throws JSONException {
        Response response = expect().statusCode(200).given().header("Content-Type", "application/hal+json")
                .get(baseURL.toString() + "products?pageSize=10");

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

    @RunAsClient
    @Test
    public void json_home() {
        Response response = expect().statusCode(200).given().header("Content-Type", "application/json-home")
                .get(baseURL.toString());

        Assertions.assertThat(response.asString()).isNotEmpty();
    }

    @RunAsClient
    @Test
    public void validate_pagination() {
        expect().statusCode(400).given().header("Content-Type", "application/hal+json")
                .get(baseURL.toString() + "products?pageSize=0");

        expect().statusCode(400).given().header("Content-Type", "application/hal+json")
                .get(baseURL.toString() + "products?pageIndex=-1");
    }

    private void expectedLinks(JSONObject obj) throws JSONException {
        JSONObject links = new JSONObject();
        links.put("self", new JSONObject().put("href", "/products?pageIndex=0&pageSize=10"));
        links.put("next", new JSONObject().put("href", "/products?pageIndex=1&pageSize=10"));
        obj.put("_links", links);
    }
}
