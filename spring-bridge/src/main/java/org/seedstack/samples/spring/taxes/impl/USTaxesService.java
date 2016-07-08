package org.seedstack.samples.spring.taxes.impl;

import org.seedstack.samples.spring.taxes.TaxesService;
import org.seedstack.seed.Configuration;

import javax.inject.Named;

@Named("us")
public class USTaxesService implements TaxesService {
    @Configuration("sample.taxes.us.rate")
    private double rate;

    @Override
    public double applyTaxes(double amount) {
        return amount + amount * rate;
    }
}
