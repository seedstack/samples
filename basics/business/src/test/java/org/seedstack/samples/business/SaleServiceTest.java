/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.business;

import java.time.LocalDate;
import javax.inject.Inject;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seedstack.business.domain.Repository;
import org.seedstack.business.util.inmemory.InMemory;
import org.seedstack.samples.business.application.SaleService;
import org.seedstack.samples.business.domain.order.Order;
import org.seedstack.samples.business.domain.order.OrderItem;
import org.seedstack.samples.business.domain.seller.Seller;
import org.seedstack.seed.it.SeedITRunner;

/**
 * @author pierre.thirouin@ext.mpsa.com (Pierre Thirouin)
 */
@RunWith(SeedITRunner.class)
public class SaleServiceTest {
    private static final long SELLER_ID = 1111L;
    private static final long CUSTOMER_ID = 3333L;
    private static final long ORDER_ID = 2222L;

    @Inject
    private SaleService saleService;

    @Inject
    @InMemory
    private Repository<Seller, Long> sellerLongRepository;

    @Test
    public void testCalculateBonus() {
        // Add a new seller
        sellerLongRepository.add(new Seller(SELLER_ID, LocalDate.now()));

        Order order = new Order(ORDER_ID, CUSTOMER_ID);
        order.addItem(new OrderItem(2, 444L, 50d));

        // Update the bonus with the order
        saleService.updateBonus(order, SELLER_ID);

        // Check that the seller has the expected bonus
        Seller seller = sellerLongRepository.get(SELLER_ID)
                .orElseThrow(() -> new IllegalStateException("No seller found"));
        Assertions.assertThat(seller.getMonthlyBonus()).isEqualTo(20);
    }
}
