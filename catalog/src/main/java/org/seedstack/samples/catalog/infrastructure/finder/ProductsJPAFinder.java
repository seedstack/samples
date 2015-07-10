package org.seedstack.samples.catalog.infrastructure.finder;

import com.google.inject.Inject;
import org.seedstack.business.api.interfaces.assembler.FluentAssembler;
import org.seedstack.business.api.interfaces.finder.Range;
import org.seedstack.business.jpa.BaseSimpleJpaFinder;
import org.seedstack.samples.catalog.domain.Product;
import org.seedstack.samples.catalog.rest.ProductRepresentation;
import org.seedstack.samples.catalog.rest.ProductsFinder;
import org.seedstack.seed.persistence.jpa.api.JpaUnit;
import org.seedstack.seed.transaction.api.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Map;

/**
 * @author pierre.thirouin@ext.mpsa.com (Pierre Thirouin)
 */
@Transactional
@JpaUnit("catalog-domain")
public class ProductsJPAFinder extends BaseSimpleJpaFinder<ProductRepresentation> implements ProductsFinder {

    @Inject
    private EntityManager entityManager;

    @Inject
    private FluentAssembler fluently;

    @Override
    protected List<ProductRepresentation> computeResultList(Range range, Map<String, Object> criteria) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> q = cb.createQuery(Product.class);
        q.select(q.from(Product.class));
        List<Product> products;

        if (range != null) {
            products = entityManager.createQuery(q).setFirstResult((int) range.getOffset()).setMaxResults(range.getSize()).getResultList();
        } else {
            products = entityManager.createQuery(q).getResultList();
        }

        return fluently.assemble(products).to(ProductRepresentation.class);
    }

    @Override
    protected long computeFullRequestSize(Map<String, Object> criteria) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> q = cb.createQuery(Long.class);
        q.select(cb.count(q.from(Product.class)));
        return entityManager.createQuery(q).getSingleResult();
    }
}
