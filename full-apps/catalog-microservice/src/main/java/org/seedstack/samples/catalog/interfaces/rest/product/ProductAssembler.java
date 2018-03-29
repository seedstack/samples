/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.catalog.interfaces.rest.product;

import javax.inject.Inject;
import org.modelmapper.ModelMapper;
import org.seedstack.business.modelmapper.ModelMapperAssembler;
import org.seedstack.samples.catalog.domain.model.product.Product;
import org.seedstack.samples.catalog.interfaces.rest.CatalogRels;
import org.seedstack.seed.rest.RelRegistry;

public class ProductAssembler extends ModelMapperAssembler<Product, ProductRepresentation> {
    @Inject
    private RelRegistry relRegistry;

    @Override
    protected void configure(ModelMapper modelMapper) {
        modelMapper.createTypeMap(Product.class, ProductRepresentation.class).addMappings(mapper -> {
            mapper.using(ctx -> relRegistry.uri(CatalogRels.PRODUCT).set("title", ctx.getSource()).getHref())
                    .map(Product::getName, ProductRepresentation::setSelf);
            mapper.using(ctx -> relRegistry.uri(CatalogRels.PRODUCT_TAGS).set("title", ctx.getSource()).getHref())
                    .map(Product::getName, ProductRepresentation::setTags);
            mapper.using(ctx -> relRegistry.uri(CatalogRels.PRODUCT_RELATED).set("title", ctx.getSource()).getHref())
                    .map(Product::getName, ProductRepresentation::setRelated);
        });
    }
}
