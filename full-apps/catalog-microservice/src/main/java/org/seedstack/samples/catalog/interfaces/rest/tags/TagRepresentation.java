/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.catalog.interfaces.rest.tags;

import org.seedstack.business.view.PaginatedView;
import org.seedstack.samples.catalog.interfaces.rest.product.ProductRepresentation;
import org.seedstack.seed.rest.hal.HalRepresentation;

public class TagRepresentation extends HalRepresentation {
    private String name;
    private long totalItems;
    private long currentPage;

    TagRepresentation() {
        // required by jackson
    }

    public TagRepresentation(String tagName, PaginatedView<ProductRepresentation> view) {
        this.name = tagName;
        this.totalItems = view.getResultSize();
        this.currentPage = view.getPageIndex();
        if (view.getView().size() > 0) {
            embedded("products", view.getView());
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTotalItems() {
        return totalItems;
    }

    public long getCurrentPage() {
        return currentPage;
    }
}
