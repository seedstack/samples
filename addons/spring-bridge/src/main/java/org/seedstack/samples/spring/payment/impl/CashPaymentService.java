/**
 * Copyright (c) 2013-2016, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.samples.spring.payment.impl;

import org.seedstack.samples.spring.payment.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CashPaymentService implements PaymentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CashPaymentService.class);

    @Override
    public String getType() {
        return "cash";
    }

    @Override
    public void pay(double amount) {
        LOGGER.info("Paying {} with cash", amount);
    }
}
