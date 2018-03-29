/**
 * Copyright (c) 2013-2015, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.samples.business.domain.order;


import org.seedstack.business.domain.BaseValueObject;

public class OrderItem extends BaseValueObject {
    private final int quantity;
    private final long productId;
    private final double price;

    public OrderItem(int quantity, long productId, double price) {
        if (quantity > 0) {
            this.quantity = quantity;
        } else {
            throw new IllegalArgumentException("The quantity cannot be lower than 1");
        }
        this.productId = productId;
        if (price >= 0) {
            this.price = price;
        } else {
            throw new IllegalArgumentException("The price cannot be lower than 0");
        }
    }

    public int getQuantity() {
        return quantity;
    }

    public long getProductId() {
        return productId;
    }

    public double getPrice() {
        return price;
    }
}
