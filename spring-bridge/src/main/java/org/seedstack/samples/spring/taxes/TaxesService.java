package org.seedstack.samples.spring.taxes;

import org.seedstack.business.Service;

@Service
public interface TaxesService {
    double applyTaxes(double amount);
}
