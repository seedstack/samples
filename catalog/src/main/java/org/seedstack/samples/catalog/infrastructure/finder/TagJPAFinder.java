package org.seedstack.samples.catalog.infrastructure.finder;

import com.google.inject.Inject;
import org.seedstack.business.api.interfaces.assembler.FluentAssembler;
import org.seedstack.business.api.interfaces.finder.Range;
import org.seedstack.business.jpa.BaseJpaRangeFinder;
import org.seedstack.samples.catalog.domain.product.Product;
import org.seedstack.samples.catalog.infrastructure.Config;
import org.seedstack.samples.catalog.rest.product.ProductRepresentation;
import org.seedstack.samples.catalog.rest.tags.TagFinder;
import org.seedstack.seed.persistence.jpa.api.JpaUnit;
import org.seedstack.seed.transaction.api.Transactional;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Map;

/**
 * @author pierre.thirouin@ext.mpsa.com (Pierre Thirouin)
 */
@Named("tag")
@Transactional
@JpaUnit(Config.JPA_UNIT)
public class TagJPAFinder extends BaseJpaRangeFinder<ProductRepresentation> implements TagFinder {

    @Inject
    private EntityManager entityManager;

    @Inject
    private FluentAssembler fluently;

    @Override
    protected List<ProductRepresentation> computeResultList(Range range, Map<String, Object> criteria) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> q = cb.createQuery(Product.class);
        String tagName = (String) criteria.get("tagName");
        Root<Product> root = q.from(Product.class);
        q.select(root)
                .where(cb.isMember(tagName, root.<List<String>>get("tags")));

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
        Root<Product> root = q.from(Product.class);
        String tagName = (String) criteria.get("tagName");
        q.select(cb.count(root))
                .where(cb.isMember(tagName, root.<List<String>>get("tags")));

        return entityManager.createQuery(q).getSingleResult();
    }
}
