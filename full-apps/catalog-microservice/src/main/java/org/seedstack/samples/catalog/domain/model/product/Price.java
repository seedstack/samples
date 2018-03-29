/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.catalog.domain.model.product;

import javax.persistence.Embeddable;
import org.seedstack.business.domain.BaseValueObject;

/**
 * A price value object.
 */
@Embeddable
public class Price extends BaseValueObject {

    private float amount;

    private String currency;

    Price() {
    }

    /**
     * Constructor.
     *
     * @param amount   the amount
     * @param currency the currency
     */
    public Price(float amount, String currency) {
        if (amount < 0) {
            throw new IllegalArgumentException("The price amount can't be negative: " + amount);
        }
        this.amount = amount;
        this.currency = currency;
    }

    /**
     * @return the amount
     */
    public float getAmount() {
        return amount;
    }

    /**
     * @return the currency
     */
    public String getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return amount + " " + currency;
    }
}
