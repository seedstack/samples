package org.seedstack.samples.catalog.infrastructure;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seedstack.seed.it.SeedITRunner;
import org.seedstack.seed.rest.api.RelRegistry;

import javax.inject.Inject;

/**
 * @author pierre.thirouin@ext.mpsa.com (Pierre Thirouin)
 */
@RunWith(SeedITRunner.class)
public class InjectionTest {

    @Inject
    private RelRegistry relRegistry;

    @Test
    public void test() {
        Assertions.assertThat(relRegistry).isNotNull();
    }
}
