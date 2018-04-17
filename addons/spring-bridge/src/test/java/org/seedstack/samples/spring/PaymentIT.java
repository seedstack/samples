/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.spring;

import com.google.inject.name.Named;
import javax.inject.Inject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seedstack.samples.spring.payment.PaymentServiceFactory;
import org.seedstack.seed.testing.junit4.internal.JUnit4Runner;

@RunWith(JUnit4Runner.class)
public class PaymentIT {
    @Inject
    @Named("paymentServiceFactory")
    private PaymentServiceFactory paymentServiceFactory;

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidPaymentMethod() {
        paymentServiceFactory.lookup("dummy");
    }

    @Test
    public void testCashPayment() {
        paymentServiceFactory.lookup("cash").pay(25);
    }

    @Test
    public void testCreditCardPayment() {
        paymentServiceFactory.lookup("cc").pay(50);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTooLowCreditCardPayment() {
        paymentServiceFactory.lookup("cc").pay(15);
    }
}
