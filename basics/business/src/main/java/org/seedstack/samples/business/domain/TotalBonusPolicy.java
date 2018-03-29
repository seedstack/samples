/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.business.domain;

import javax.inject.Named;
import org.seedstack.samples.business.domain.order.Order;

@Named(BonusPolicy.TOTAL_PERCENTAGE)
class TotalBonusPolicy implements BonusPolicy {
    private static final double BONUS_RATE = 0.02;

    @Override
    public double computeBonus(Order order) {
        return order.getTotal() * BONUS_RATE;
    }
}
