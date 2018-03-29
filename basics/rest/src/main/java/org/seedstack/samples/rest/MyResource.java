/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.seedstack.seed.Application;
import org.seedstack.seed.rest.Rel;

@Path("/greeter")
public class MyResource {
    @Inject
    private Application application;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{name}")
    @Rel(value = "greeter", home = true)
    public String doGet(@PathParam("name") String name) {
        return String.format("Hello %s from %s!", name, application.getId());
    }
}
