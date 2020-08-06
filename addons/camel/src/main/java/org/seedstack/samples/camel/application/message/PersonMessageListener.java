/*
 *  Copyright © 2013-2020, The SeedStack authors <http://seedstack.org>
 *
 *  This Source Code Form is subject to the terms of the Mozilla Public
 *   License, v. 2.0. If a copy of the MPL was not distributed with this
 *   file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.camel.application.message;

import org.seedstack.jms.JmsMessageListener;
import org.seedstack.samples.camel.domain.model.person.Person;
import org.seedstack.seed.Logging;
import org.slf4j.Logger;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

@JmsMessageListener(connection = "connection1", destinationName = "queue1")
public class PersonMessageListener implements MessageListener {
    @Logging
    private Logger logger;



    @Override
    public void onMessage(Message message) {
        if (message instanceof ObjectMessage) {
            logger.info("Message received !");
        } else {
            logger.warn("Unsupported message type");
        }

    }
}
