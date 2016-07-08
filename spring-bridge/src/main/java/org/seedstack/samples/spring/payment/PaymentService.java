package org.seedstack.samples.spring.payment;

public interface PaymentService {

    String getType();

    void pay(double amount);

}
