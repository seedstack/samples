/**
 * Copyright (c) 2013-2015, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.samples.business.domain.seller;


import org.seedstack.samples.business.domain.BonusPolicy;
import org.junit.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class SellerTest {
    private static final Long SELLER_ID = 1L;
    private Seller underTest;

    @Test
    public void testInitialState() throws Exception {
        underTest = new Seller(SELLER_ID, LocalDate.now().minus(30, ChronoUnit.DAYS));
        assertThat(underTest.getBonusPolicy()).isEqualTo(BonusPolicy.PER_ITEM);
        assertThat(underTest.getMonthlyBonus()).isEqualTo(0);
    }

    @Test(expected = IllegalStateException.class)
    public void testPercentagePolicyIsForbiddenForJuniors() throws Exception {
        underTest = new Seller(SELLER_ID, LocalDate.now().minus(30, ChronoUnit.DAYS));
        underTest.enablePercentageBonusPolicy();
    }

    @Test
    public void testPercentagePolicyIsAllowedForSeniors() throws Exception {
        underTest = new Seller(SELLER_ID, LocalDate.now().minus(Seller.SENIORITY_THRESHOLD, ChronoUnit.DAYS));
        underTest.enablePercentageBonusPolicy();
    }

    @Test
    public void testBonusCompute() throws Exception {
        underTest = new Seller(SELLER_ID, LocalDate.now().minus(Seller.SENIORITY_THRESHOLD, ChronoUnit.DAYS));
        underTest.addToMonthlyBonus(450);
        underTest.addToMonthlyBonus(50);
        assertThat(underTest.getMonthlyBonus()).isEqualTo(500);
        underTest.resetMonthlyBonus();
        assertThat(underTest.getMonthlyBonus()).isZero();
    }
}