/*
 *  Copyright © 2013-2020, The SeedStack authors <http://seedstack.org>
 *
 *  This Source Code Form is subject to the terms of the Mozilla Public
 *   License, v. 2.0. If a copy of the MPL was not distributed with this
 *   file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.camel.application.routes.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.seedstack.seed.Logging;
import org.slf4j.Logger;

/**
 * Camel processor
 */
public class PersonQueueProcessor implements Processor {

    @Logging
    Logger logger;

    @Override
    public void process(Exchange exchange) throws Exception {
        logger.info("Exchange treated in processor");
    }
}
