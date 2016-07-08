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
