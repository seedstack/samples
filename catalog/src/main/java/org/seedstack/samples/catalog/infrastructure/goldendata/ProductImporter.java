package org.seedstack.samples.catalog.infrastructure.goldendata;

import org.seedstack.business.api.domain.Repository;
import org.seedstack.samples.catalog.domain.Product;
import org.seedstack.seed.core.spi.data.DataImporter;
import org.seedstack.seed.core.spi.data.DataSet;
import org.seedstack.seed.persistence.jpa.api.Jpa;
import org.seedstack.seed.persistence.jpa.api.JpaUnit;
import org.seedstack.seed.transaction.api.Transactional;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * @author pierre.thirouin@ext.mpsa.com (Pierre Thirouin)
 */
@DataSet(group="catalog", name="product")
public class ProductImporter implements DataImporter<Product> {

    @Inject @Jpa
    private Repository<Product, String> repository;

    private List<Product> stating = new ArrayList<Product>();

    @Override
    public boolean isInitialized() {
        return false;
    }

    @Override
    public void importData(Product data) {
        stating.add(data);
    }

    @JpaUnit("catalog-domain")
    @Transactional
    @Override
    public void commit(boolean clear) {
        for (Product product : stating) {
            repository.persist(product);
        }
    }

    @Override
    public void rollback() {
        stating.clear();
    }
}
