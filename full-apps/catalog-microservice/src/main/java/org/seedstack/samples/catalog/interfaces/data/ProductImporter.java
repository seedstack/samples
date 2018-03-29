/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.catalog.interfaces.data;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import javax.inject.Inject;
import org.seedstack.business.data.BaseDataImporter;
import org.seedstack.business.domain.Repository;
import org.seedstack.jpa.Jpa;
import org.seedstack.jpa.JpaUnit;
import org.seedstack.samples.catalog.application.Config;
import org.seedstack.samples.catalog.domain.model.product.Product;
import org.seedstack.seed.transaction.Transactional;

class ProductImporter extends BaseDataImporter<Product> {
    private static final int MAXIMUM_ITERATION = 15;
    @Inject
    @Jpa
    private Repository<Product, String> repository;
    private List<Product> staging = new ArrayList<>();
    private SecureRandom random = new SecureRandom();

    @Override
    public boolean isInitialized() {
        return false;
    }

    @Transactional
    @JpaUnit(Config.JPA_UNIT)
    @Override
    public void clear() {
        staging.clear();
        repository.clear();
    }

    @Transactional
    @JpaUnit(Config.JPA_UNIT)
    @Override
    public void importData(Stream<Product> stream) {
        stream.forEach(staging::add);
        staging.forEach(this::addRelatedProducts);
        staging.forEach(repository::add);
    }

    private void addRelatedProducts(Product product) {
        int iteration = 0;
        // Iterate until it finds 4 unique related products
        while (product.getRelated().size() < 4 && iteration < MAXIMUM_ITERATION) {
            Product relatedProduct = staging.get(random.nextInt(staging.size()));
            product.addRelated(relatedProduct.getId());
            iteration++;
        }
    }
}
