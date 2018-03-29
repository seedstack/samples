/**
 * Copyright (c) 2013-2016, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.samples.w20.rest;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/sample/greeter")
public class GreeterResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Message computeMessage(@QueryParam("name") @DefaultValue("no one") String name) {
        return new Message(String.format("Hello %s!", name));
    }
}
