/*
 *  Copyright © 2013-2020, The SeedStack authors <http://seedstack.org>
 *
 *  This Source Code Form is subject to the terms of the Mozilla Public
 *   License, v. 2.0. If a copy of the MPL was not distributed with this
 *   file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.camel.application.message;

import org.seedstack.business.Service;
import org.seedstack.samples.camel.domain.model.person.Person;

@Service
public interface MessageService {
    public static final String QUEUE_STD_NAME="queue1";
    public static final String QUEUE_CAMEL_NAME="camelQueue";
    /**
     * Sends a person to the Message Queue
     * @param person the person item to send
     * @param queueName the queue name
     */
    void sendPersonMessage(Person person, String queueName);

}
