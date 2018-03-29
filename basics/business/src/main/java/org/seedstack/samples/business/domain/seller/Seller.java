/**
 * Copyright (c) 2013-2015, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.samples.business.domain.seller;

import org.seedstack.samples.business.domain.BonusPolicy;
import org.seedstack.business.domain.BaseAggregateRoot;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Seller extends BaseAggregateRoot<Long> {
    public static final int SENIORITY_THRESHOLD = 90;

    private final Long sellerId;
    private final LocalDate hireDate;
    private String bonusPolicy = BonusPolicy.PER_ITEM;
    private double monthlyBonus = 0;

    public Seller(Long sellerId, LocalDate hireDate) {
        this.sellerId = sellerId;
        this.hireDate = hireDate;
    }

    @Override
    public Long getEntityId() {
        return sellerId;
    }

    public void enablePercentageBonusPolicy() {
        if (hireDate.until(LocalDate.now(), ChronoUnit.DAYS) < SENIORITY_THRESHOLD) {
            throw new IllegalStateException("Percentage bonus policy requires at least 3 years of seniority");
        }
        bonusPolicy = BonusPolicy.TOTAL_PERCENTAGE;
    }

    public void revertBonusPolicy() {
        bonusPolicy = BonusPolicy.PER_ITEM;
    }

    public String getBonusPolicy() {
        return bonusPolicy;
    }

    public void addToMonthlyBonus(double newBonus) {
        this.monthlyBonus += newBonus;
    }

    public void resetMonthlyBonus() {
        this.monthlyBonus = 0;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public double getMonthlyBonus() {
        return monthlyBonus;
    }
}
