/**
 * Copyright (c) 2013-2015, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.samples.business.domain.order;

import org.seedstack.business.domain.BaseAggregateRoot;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Order extends BaseAggregateRoot<Long> {
    private final Long orderId;
    private final Long customerId;
    private Date checkoutDate;
    private final Set<OrderItem> items = new HashSet<>();
    private double total;

    public Order(Long orderId, Long customerId) {
        this.orderId = orderId;
        this.customerId = customerId;
    }

    @Override
    public Long getEntityId() {
        return orderId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public void addItem(OrderItem orderItem) {
        items.add(orderItem);
        total += orderItem.getQuantity() * orderItem.getPrice();
    }

    public void removeItem(OrderItem orderItem) {
        items.remove(orderItem);
        total -= orderItem.getQuantity() * orderItem.getPrice();
    }

    public Set<OrderItem> getItems() {
        return Collections.unmodifiableSet(items);
    }

    public double getTotal() {
        return total;
    }
}
