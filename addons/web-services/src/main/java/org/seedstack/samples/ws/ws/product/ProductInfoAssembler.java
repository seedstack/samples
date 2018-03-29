/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.ws.ws.product;

import org.modelmapper.ModelMapper;
import org.seedstack.business.modelmapper.ModelMapperAssembler;
import org.seedstack.samples.ws.domain.model.Product;
import org.seedstack.samples.ws.product.ProductInfo;

public class ProductInfoAssembler extends ModelMapperAssembler<Product, ProductInfo> {
    @Override
    protected void configure(ModelMapper modelMapper) {
        modelMapper
                .createTypeMap(Product.class, ProductInfo.class)
                // Required due to the ambiguity with the categoryId field
                .addMapping(Product::getId, ProductInfo::setId);
    }
}
