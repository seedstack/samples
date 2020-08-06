/*
 *  Copyright © 2013-2020, The SeedStack authors <http://seedstack.org>
 *
 *  This Source Code Form is subject to the terms of the Mozilla Public
 *   License, v. 2.0. If a copy of the MPL was not distributed with this
 *   file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.camel.application.routes.initializer;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.component.jms.JmsComponent;
import org.seedstack.camel.CamelContextInitializer;
import org.seedstack.jms.spi.JmsFactory;
import org.seedstack.seed.Logging;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.Session;

public class CamelApplicationInitializer implements CamelContextInitializer {

    @Logging
    private Logger logger;

    @Override
    public void initialize(CamelContext camelContext) {
        logger.info("Camel plugin sample - Initializing camel context");
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost?broker.persistent=false");
        camelContext.addComponent("jmsComponent", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
    }
}
