/**
 * Copyright (c) 2013-2016, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.samples.spring;

import com.google.inject.name.Named;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seedstack.samples.spring.payment.PaymentServiceFactory;
import org.seedstack.seed.it.SeedITRunner;

import javax.inject.Inject;

@RunWith(SeedITRunner.class)
public class PaymentIT {
    @Inject
    @Named("paymentServiceFactory")
    private PaymentServiceFactory paymentServiceFactory;

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidPaymentMethod() throws Exception {
        paymentServiceFactory.lookup("dummy");
    }

    @Test
    public void testCashPayment() throws Exception {
        paymentServiceFactory.lookup("cash").pay(25);
    }

    @Test
    public void testCreditCardPayment() throws Exception {
        paymentServiceFactory.lookup("cc").pay(50);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTooLowCreditCardPayment() throws Exception {
        paymentServiceFactory.lookup("cc").pay(15);
    }
}
