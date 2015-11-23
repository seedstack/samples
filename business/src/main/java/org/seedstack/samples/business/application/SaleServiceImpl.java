/**
 * Copyright (c) 2013-2015, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.samples.business.application;

import org.seedstack.samples.business.domain.BonusPolicy;
import org.seedstack.samples.business.domain.order.Order;
import org.seedstack.samples.business.domain.seller.Seller;
import org.seedstack.business.domain.DomainRegistry;
import org.seedstack.business.domain.Repository;

import javax.inject.Inject;

class SaleServiceImpl implements SaleService {
    private final Repository<Seller, Long> sellerRepository;
    private final DomainRegistry domainRegistry;

    @Inject
    public SaleServiceImpl(Repository<Seller, Long> sellerRepository, DomainRegistry domainRegistry) {
        this.sellerRepository = sellerRepository;
        this.domainRegistry = domainRegistry;
    }

    @Override
    public void updateBonus(Order order, Long sellerId) {
        Seller seller = sellerRepository.load(sellerId);
        if (seller == null) {
            throw new IllegalArgumentException("Seller " + sellerId +  " cannot be found");
        }

        BonusPolicy bonusPolicy = domainRegistry.getPolicy(BonusPolicy.class, seller.getBonusPolicy());

        double orderBonus = bonusPolicy.computeBonus(order);
        seller.addToMonthlyBonus(orderBonus);

        sellerRepository.save(seller);
    }
}
