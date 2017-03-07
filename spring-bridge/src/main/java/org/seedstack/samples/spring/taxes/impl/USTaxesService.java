/**
 * Copyright (c) 2013-2016, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.samples.spring.taxes.impl;

import org.seedstack.samples.spring.taxes.TaxesService;
import org.seedstack.seed.Configuration;

import javax.inject.Named;

@Named("us")
public class USTaxesService implements TaxesService {
    @Configuration("sample.taxes.us")
    private double rate;

    @Override
    public double applyTaxes(double amount) {
        return amount + amount * rate;
    }
}
