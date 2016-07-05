package org.seedstack.samples.seed.rest;

import org.hamcrest.Matchers;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.net.URL;

import static com.jayway.restassured.RestAssured.expect;

@RunWith(Arquillian.class)
public class RestIT {
    @ArquillianResource
    private URL baseURL;

    @Deployment
    public static WebArchive deploy() {
        return ShrinkWrap.create(WebArchive.class);
    }

    @Test
    @RunAsClient
    public void testGreeter() throws Exception {
        expect().statusCode(200).body(Matchers.equalTo("Hello World!")).get(baseURL + "greeter/World");
    }
}
