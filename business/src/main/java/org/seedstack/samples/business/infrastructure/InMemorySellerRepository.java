/**
 * Copyright (c) 2013-2015, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.samples.business.infrastructure;

import org.seedstack.samples.business.domain.seller.Seller;
import org.seedstack.business.domain.BaseRepository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class InMemorySellerRepository extends BaseRepository<Seller, Long> {

    private static final ConcurrentMap<Long, Seller> sellers = new ConcurrentHashMap<>();

    @Override
    protected Seller doLoad(Long sellerId) {
        return sellers.get(sellerId);
    }

    @Override
    protected void doDelete(Long sellerId) {
        sellers.remove(sellerId);
    }

    @Override
    protected void doDelete(Seller seller) {
        doDelete(seller.getEntityId());
    }

    @Override
    protected void doPersist(Seller seller) {
        if (sellers.putIfAbsent(seller.getEntityId(), seller) != null) {
            throw new IllegalStateException(String.format("Seller %d is already persisted", seller.getEntityId()));
        }
    }

    @Override
    protected Seller doSave(Seller seller) {
        sellers.put(seller.getEntityId(), seller);
        return seller;
    }
}
