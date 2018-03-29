/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.ws.domain.model;

import org.seedstack.business.domain.BaseAggregateRoot;

public class Product extends BaseAggregateRoot<Long> {
    private final Long id;
    private final String designation;

    public Product(long entityId, String designation) {
        this.id = entityId;
        this.designation = designation;
    }

    @Override
    public Long getId() {
        return id;
    }

    /**
     * Gets the designation.
     *
     * @return the designation
     */
    public String getDesignation() {
        return designation;
    }

    /**
     * Gets the summary.
     *
     * @return the summary
     */
    public String getSummary() {
        return "Summary of product #" + id;
    }

    /**
     * Gets the details.
     *
     * @return the details
     */
    public String getDetails() {
        return "Details of product #" + id;
    }

    /**
     * Gets the picture.
     *
     * @return the picture
     */
    public String getPicture() {
        return "https://placehold.it/350x150";
    }

    /**
     * Gets the price.
     *
     * @return the price
     */
    public Double getPrice() {
        return id * 10.0 + 1;
    }

    /**
     * Gets the categoryId.
     *
     * @return the categoryId
     */
    public Long getCategoryId() {
        return id * 100 % 20 + 1;
    }
}
