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
