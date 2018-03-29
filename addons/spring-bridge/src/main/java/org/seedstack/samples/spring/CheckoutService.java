/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.spring;

import org.seedstack.samples.spring.payment.PaymentService;
import org.seedstack.samples.spring.payment.PaymentServiceFactory;
import org.seedstack.samples.spring.taxes.TaxesService;

public class CheckoutService {
    private final PaymentServiceFactory paymentServiceFactory;
    private final TaxesService taxesService;

    public CheckoutService(PaymentServiceFactory paymentServiceFactory, TaxesService taxesService) {
        this.paymentServiceFactory = paymentServiceFactory;
        this.taxesService = taxesService;
    }

    public double checkout(double amount, String paymentType) {
        PaymentService paymentService = paymentServiceFactory.lookup(paymentType);
        double amountWithTaxes = taxesService.applyTaxes(amount);
        paymentService.pay(amountWithTaxes);
        return amountWithTaxes;
    }
}
