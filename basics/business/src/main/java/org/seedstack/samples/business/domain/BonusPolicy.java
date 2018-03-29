/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.business.domain;

import org.seedstack.business.domain.DomainPolicy;
import org.seedstack.samples.business.domain.order.Order;

@DomainPolicy
public interface BonusPolicy {
    String PER_ITEM = "item";
    String TOTAL_PERCENTAGE = "percentage";

    double computeBonus(Order order);
}
