package org.seedstack.samples.spring.payment.impl;

import org.seedstack.samples.spring.payment.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreditCardPaymentService implements PaymentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CreditCardPaymentService.class);
    private double minimum = 0;

    public void setMinimum(double minimum) {
        this.minimum = minimum;
    }

    @Override
    public String getType() {
        return "cc";
    }

    @Override
    public void pay(double amount) {
        if (amount < minimum) {
            throw new IllegalArgumentException(String.format("Cannot pay less than %s with credit card", minimum));
        }
        LOGGER.info("Paying {} with credit card", amount);
    }
}
