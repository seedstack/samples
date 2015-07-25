package org.seedstack.samples.catalog.infrastructure;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seedstack.business.api.domain.Factory;
import org.seedstack.business.api.domain.Repository;
import org.seedstack.samples.catalog.domain.product.Price;
import org.seedstack.samples.catalog.domain.product.Product;
import org.seedstack.seed.it.SeedITRunner;
import org.seedstack.seed.persistence.jpa.api.Jpa;
import org.seedstack.seed.persistence.jpa.api.JpaUnit;
import org.seedstack.seed.transaction.api.Transactional;

import javax.inject.Inject;

/**
 * @author pierre.thirouin@ext.mpsa.com (Pierre Thirouin)
 */
@RunWith(SeedITRunner.class)
public class ProductRepositoryIT {

    @Inject @Jpa
    private Repository<Product, String> repository;

    @Inject
    private Factory<Product> factory;

    @Test
    @Transactional
    @JpaUnit(Config.JPA_UNIT)
    public void test_database_config() {
        Product product = factory.create("SeedStack in Action");
        product.setDescription("Book presenting seedstack and all its awesome features");
        product.setPricing(new Price(45, "euro"));
        repository.persist(product);

        Product product1 = repository.load("SeedStack in Action");
        Assertions.assertThat(product1).isNotNull();
        Assertions.assertThat(product1.getDescription()).isEqualTo("Book presenting seedstack and all its awesome features");
    }
}
