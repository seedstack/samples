/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.business.domain;

import java.util.UUID;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.seedstack.samples.business.domain.order.Order;
import org.seedstack.samples.business.domain.order.OrderItem;

public class TotalBonusPolicyTest {
    private TotalBonusPolicy underTest = new TotalBonusPolicy();

    @Test
    public void bonusShouldBeEqualToTheNumberOfItems() {
        assertBonus(0);
        assertBonus(10);
        assertBonus(33);
    }

    private void assertBonus(double total) {
        double bonus = underTest.computeBonus(prepareOrder(total));
        Assertions.assertThat(bonus).isEqualTo(total * .02);
    }

    private Order prepareOrder(double total) {
        Order order = new Order(1L, 2L);
        order.addItem(new OrderItem(1, UUID.randomUUID().hashCode(), total));
        return order;
    }
}
