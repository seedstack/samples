/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.catalog.interfaces.rest.tags;

import org.seedstack.business.finder.Finder;
import org.seedstack.business.finder.RangeFinder;
import org.seedstack.business.view.Page;
import org.seedstack.business.view.PaginatedView;
import org.seedstack.samples.catalog.interfaces.rest.product.ProductRepresentation;

/**
 * Provides method to find tags.
 */
@Finder
public interface TagFinder extends RangeFinder<ProductRepresentation, String> {

    /**
     * Finds the products associated to a given tag.
     *
     * @param page    the required page
     * @param tagName the tag name
     * @return a product page
     */
    PaginatedView<ProductRepresentation> findProductsByTag(Page page, String tagName);
}
