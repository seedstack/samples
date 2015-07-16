package org.seedstack.samples.catalog.infrastructure;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seedstack.business.api.interfaces.finder.Range;
import org.seedstack.business.api.interfaces.finder.Result;
import org.seedstack.samples.catalog.rest.ProductRepresentation;
import org.seedstack.samples.catalog.rest.ProductsFinder;
import org.seedstack.seed.it.SeedITRunner;

import javax.inject.Inject;

/**
 * @author pierre.thirouin@ext.mpsa.com (Pierre Thirouin)
 */
@RunWith(SeedITRunner.class)
public class ProductJpaFinderIT {

    @Inject
    private ProductsFinder productsFinder;

    @Test
    public void test_find() {
        Result<ProductRepresentation> result = productsFinder.find(new Range(0, 10), null);
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getFullSize()).isGreaterThanOrEqualTo(0);
        Assertions.assertThat(result.getSize()).isGreaterThanOrEqualTo(0);
    }
}
