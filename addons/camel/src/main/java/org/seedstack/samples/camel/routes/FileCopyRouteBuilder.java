/*
 * Copyright © 2013-2020, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.samples.camel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.seedstack.seed.Configuration;

public class FileCopyRouteBuilder extends RouteBuilder {

    @Configuration("sample.route.from")
    private String origin;
    @Configuration("sample.route.to")
    private String destination;

    @Override
    public void configure() throws Exception {
        from(origin).to(destination);
    }
}
