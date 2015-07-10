package org.seedstack.samples.catalog.domain;

import org.seedstack.business.api.domain.BaseValueObject;

import javax.persistence.Embeddable;

/**
 * @author pierre.thirouin@ext.mpsa.com (Pierre Thirouin)
 */
@Embeddable
public class Price extends BaseValueObject {

    private String amount;

    private String currency;

    Price() {
    }

    public Price(String amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public String getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }
}
