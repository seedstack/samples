/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.business.domain.order;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.seedstack.business.domain.BaseAggregateRoot;

public class Order extends BaseAggregateRoot<Long> {
    private final Long orderId;
    private final Long customerId;
    private final Set<OrderItem> items = new HashSet<>();
    private Date checkoutDate;
    private double total;

    public Order(Long orderId, Long customerId) {
        this.orderId = orderId;
        this.customerId = customerId;
    }

    @Override
    public Long getId() {
        return orderId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
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
