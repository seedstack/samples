/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.business.domain.seller;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import org.junit.Test;
import org.seedstack.samples.business.domain.BonusPolicy;

public class SellerTest {
    private static final Long SELLER_ID = 1L;
    private Seller underTest;

    @Test
    public void testInitialState() {
        underTest = new Seller(SELLER_ID, LocalDate.now().minus(30, ChronoUnit.DAYS));
        assertThat(underTest.getBonusPolicy()).isEqualTo(BonusPolicy.PER_ITEM);
        assertThat(underTest.getMonthlyBonus()).isEqualTo(0);
    }

    @Test(expected = IllegalStateException.class)
    public void testPercentagePolicyIsForbiddenForJuniors() {
        underTest = new Seller(SELLER_ID, LocalDate.now().minus(30, ChronoUnit.DAYS));
        underTest.enablePercentageBonusPolicy();
    }

    @Test
    public void testPercentagePolicyIsAllowedForSeniors() {
        underTest = new Seller(SELLER_ID, LocalDate.now().minus(Seller.SENIORITY_THRESHOLD, ChronoUnit.DAYS));
        underTest.enablePercentageBonusPolicy();
    }

    @Test
    public void testBonusCompute() {
        underTest = new Seller(SELLER_ID, LocalDate.now().minus(Seller.SENIORITY_THRESHOLD, ChronoUnit.DAYS));
        underTest.addToMonthlyBonus(450);
        underTest.addToMonthlyBonus(50);
        assertThat(underTest.getMonthlyBonus()).isEqualTo(500);
        underTest.resetMonthlyBonus();
        assertThat(underTest.getMonthlyBonus()).isZero();
    }
}