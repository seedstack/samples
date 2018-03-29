/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.business.application;

import javax.inject.Inject;
import org.seedstack.business.domain.DomainRegistry;
import org.seedstack.business.domain.Repository;
import org.seedstack.business.util.inmemory.InMemory;
import org.seedstack.samples.business.domain.BonusPolicy;
import org.seedstack.samples.business.domain.order.Order;
import org.seedstack.samples.business.domain.seller.Seller;

class SaleServiceImpl implements SaleService {
    private final Repository<Seller, Long> sellerRepository;
    private final DomainRegistry domainRegistry;

    @Inject
    public SaleServiceImpl(@InMemory Repository<Seller, Long> sellerRepository, DomainRegistry domainRegistry) {
        this.sellerRepository = sellerRepository;
        this.domainRegistry = domainRegistry;
    }

    @Override
    public void updateBonus(Order order, Long sellerId) {
        Seller seller = sellerRepository.get(sellerId)
                .orElseThrow(() -> new IllegalArgumentException("Seller " + sellerId + " cannot be found"));

        BonusPolicy bonusPolicy = domainRegistry.getPolicy(BonusPolicy.class, seller.getBonusPolicy());

        double orderBonus = bonusPolicy.computeBonus(order);
        seller.addToMonthlyBonus(orderBonus);

        sellerRepository.update(seller);
    }
}
