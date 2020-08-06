/*
 *  Copyright © 2013-2020, The SeedStack authors <http://seedstack.org>
 *
 *  This Source Code Form is subject to the terms of the Mozilla Public
 *   License, v. 2.0. If a copy of the MPL was not distributed with this
 *   file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.camel.application.routes;

import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.seedstack.samples.camel.application.routes.processors.PersonQueueProcessor;

import javax.inject.Inject;

/**
 * This Camel route uses Camel JMS component to add a person to the repository
 */
public class PersonRouteBuilder extends RouteBuilder {

    @Inject
    private PersonQueueProcessor processor;

    @Override
    public void configure() throws Exception {
        from("jmsComponent:queue:camelQueue").process(processor).to("mock:noway");
    }
}
