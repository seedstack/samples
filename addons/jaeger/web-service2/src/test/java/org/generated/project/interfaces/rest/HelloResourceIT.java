package org.generated.project.interfaces.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seedstack.seed.Configuration;
import org.seedstack.seed.testing.junit4.SeedITRunner;
import org.seedstack.seed.undertow.LaunchWithUndertow;

@RunWith(SeedITRunner.class)
@LaunchWithUndertow
public class HelloResourceIT {
    @Configuration("runtime.web.baseUrl")
    private String baseUrl;

    @Test
    public void testHelloWorld() throws Exception {
        // Response response = given().auth().basic("demo", "demo").expect().statusCode(200).when().get(baseUrl + "/hello");

        // assertThat(response.body().asString()).isEqualTo("Hello World!");
    }
}
