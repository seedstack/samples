/*
 *  Copyright © 2013-2020, The SeedStack authors <http://seedstack.org>
 *
 *  This Source Code Form is subject to the terms of the Mozilla Public
 *   License, v. 2.0. If a copy of the MPL was not distributed with this
 *   file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.camel.application.message;

import org.seedstack.jms.JmsConnection;
import org.seedstack.samples.camel.application.error.CamelSampleErrorCode;
import org.seedstack.samples.camel.domain.model.person.Person;
import org.seedstack.seed.Logging;
import org.seedstack.seed.SeedException;
import org.seedstack.seed.transaction.Transactional;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.jms.*;

public class MessageServiceImpl implements MessageService {

    @Logging
    private Logger logger;

    @Inject
    private Session session;

    @Transactional
    @JmsConnection("connection1")
    @Override
    public void sendPersonMessage(Person person, String queueName) {
        try {
            logger.info("Adding person to JMS Queue");
            Destination queue = session.createQueue(queueName);
            MessageProducer producer = session.createProducer(queue);
            ObjectMessage message = session.createObjectMessage();
            message.setObject(person);
            producer.send(message);
        }
        catch (JMSException jsmE){
            throw SeedException.wrap(jsmE, CamelSampleErrorCode.ERR_JMS);
        }
    }

}
