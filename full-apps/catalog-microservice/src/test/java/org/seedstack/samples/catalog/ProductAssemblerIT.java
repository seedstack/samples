/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.catalog;

import javax.inject.Inject;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seedstack.business.assembler.FluentAssembler;
import org.seedstack.samples.catalog.domain.model.product.Product;
import org.seedstack.samples.catalog.interfaces.rest.product.ProductRepresentation;
import org.seedstack.seed.rest.hal.HalRepresentation;
import org.seedstack.seed.rest.hal.Link;
import org.seedstack.seed.testing.junit4.internal.JUnit4Runner;

@RunWith(JUnit4Runner.class)
public class ProductAssemblerIT {
    @Inject
    private FluentAssembler fluently;

    @Test
    public void test_assemble() {
        Product product = new Product("productName");
        HalRepresentation representation = fluently.assemble(product).to(ProductRepresentation.class);

        Assertions.assertThat(representation).isNotNull();
        Assertions.assertThat(((Link) representation.getLink("self")).getHref()).isEqualTo("/products/productName");
        Assertions.assertThat(((Link) representation.getLink("tags")).getHref())
                .isEqualTo("/products/productName/tags");
    }
}
