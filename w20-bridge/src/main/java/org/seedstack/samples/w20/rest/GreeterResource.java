package org.seedstack.samples.w20.rest;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/demo/greeter")
public class GreeterResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Message computeMessage(@QueryParam("name") @DefaultValue("no one") String name) {
        return new Message(String.format("Hello %s!", name));
    }
}
