/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.spring.payment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentServiceFactory {
    private Map<String, PaymentService> paymentServices = new HashMap<>();

    public PaymentServiceFactory(List<PaymentService> paymentServices) {
        for (PaymentService paymentService : paymentServices) {
            this.paymentServices.put(paymentService.getType(), paymentService);
        }
    }

    public PaymentService lookup(String type) {
        if (!paymentServices.containsKey(type)) {
            throw new IllegalArgumentException("Not a valid payment type: " + type);
        }

        return paymentServices.get(type);
    }
}
