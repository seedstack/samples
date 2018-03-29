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
import org.seedstack.samples.business.domain.order.OrderItem;

@Named(BonusPolicy.PER_ITEM)
class ItemBonusPolicy implements BonusPolicy {
    private static final int ITEM_BONUS = 10;

    @Override
    public double computeBonus(Order order) {
        return order.getItems().stream().mapToInt(OrderItem::getQuantity).sum() * ITEM_BONUS;
    }

}
