/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.catalog.interfaces.rest;

/**
 * Lists all the REST resource provided by this application.
 */
public class CatalogRels {

    /**
     * Catalog resource. Provides a list of products.
     */
    public static final String CATALOG = "catalog";

    /**
     * Product resource. Exposes all the information about a specific product.
     */
    public static final String PRODUCT = "product";

    /**
     * Product's tags sub resource. Exposes all the tags of a product.
     */
    public static final String PRODUCT_TAGS = "tags";

    /**
     * Tag resource. Exposes all the products corresponding to a tag.
     */
    public static final String TAG = "tag";

    /**
     * All products related to another products.
     */
    public static final String PRODUCT_RELATED = "related";
}
