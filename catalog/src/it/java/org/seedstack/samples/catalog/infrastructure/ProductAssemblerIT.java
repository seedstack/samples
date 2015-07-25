package org.seedstack.samples.catalog.infrastructure;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seedstack.business.api.interfaces.assembler.FluentAssembler;
import org.seedstack.samples.catalog.domain.product.Product;
import org.seedstack.samples.catalog.rest.product.ProductRepresentation;
import org.seedstack.seed.it.SeedITRunner;

import javax.inject.Inject;

/**
 * @author pierre.thirouin@ext.mpsa.com (Pierre Thirouin)
 */
@RunWith(SeedITRunner.class)
public class ProductAssemblerIT {

    @Inject
    public FluentAssembler fluently;

    @Test
    public void test_assemble() {
        Product product = new Product("productName");
        ProductRepresentation representation = fluently.assemble(product).to(ProductRepresentation.class);
        Assertions.assertThat(representation).isNotNull();
        Assertions.assertThat(representation.getLinks().get("self").iterator().next().getHref()).isEqualTo("/products/productName");
        Assertions.assertThat(representation.getLinks().get("tags").iterator().next().getHref()).isEqualTo("/products/productName/tags");
    }
}
