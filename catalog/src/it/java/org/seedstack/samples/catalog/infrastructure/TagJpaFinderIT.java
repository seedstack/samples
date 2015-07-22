package org.seedstack.samples.catalog.infrastructure;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seedstack.business.api.interfaces.finder.Range;
import org.seedstack.business.api.interfaces.finder.Result;
import org.seedstack.samples.catalog.rest.product.ProductRepresentation;
import org.seedstack.samples.catalog.rest.tags.TagFinder;
import org.seedstack.seed.it.SeedITRunner;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.HashMap;
import java.util.Map;

/**
 * @author pierre.thirouin@ext.mpsa.com (Pierre Thirouin)
 */
@RunWith(SeedITRunner.class)
public class TagJpaFinderIT {

    @Inject @Named("tag")
    private TagFinder tagFinder;

    @Test
    public void test_find() {
        Map<String, Object> criteria = new HashMap<String, Object>();
        criteria.put("tagName", "tag2");
        Result<ProductRepresentation> result = tagFinder.find(new Range(0, 10), criteria);
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getFullSize()).isGreaterThanOrEqualTo(0);
        Assertions.assertThat(result.getSize()).isGreaterThanOrEqualTo(0);
    }
}
