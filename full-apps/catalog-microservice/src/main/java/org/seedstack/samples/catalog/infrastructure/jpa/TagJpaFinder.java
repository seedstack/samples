/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.catalog.infrastructure.jpa;

import com.google.inject.Inject;
import java.util.List;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.seedstack.business.assembler.FluentAssembler;
import org.seedstack.business.finder.BaseRangeFinder;
import org.seedstack.business.finder.Range;
import org.seedstack.business.finder.Result;
import org.seedstack.business.view.Page;
import org.seedstack.business.view.PaginatedView;
import org.seedstack.jpa.JpaUnit;
import org.seedstack.samples.catalog.application.Config;
import org.seedstack.samples.catalog.domain.model.product.Product;
import org.seedstack.samples.catalog.interfaces.rest.product.ProductRepresentation;
import org.seedstack.samples.catalog.interfaces.rest.tags.TagFinder;
import org.seedstack.seed.transaction.Transactional;

@Named("tag")
@Transactional
@JpaUnit(Config.JPA_UNIT)
class TagJpaFinder extends BaseRangeFinder<ProductRepresentation, String> implements TagFinder {
    @Inject
    private EntityManager entityManager;

    @Inject
    private FluentAssembler fluently;

    @Override
    public PaginatedView<ProductRepresentation> findProductsByTag(Page page, String tagName) {
        Result<ProductRepresentation> result = find(Range.rangeFromPageInfo(page.getIndex(), page.getCapacity()),
                tagName);
        return new PaginatedView<>(result, page);
    }

    @Override
    protected List<ProductRepresentation> computeResultList(Range range, String tagName) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> q = cb.createQuery(Product.class);
        Root<Product> root = q.from(Product.class);
        q.select(root)
                .where(cb.isMember(tagName, root.<List<String>>get("tags")));

        List<Product> products;
        if (range != null) {
            products = entityManager.createQuery(q)
                    .setFirstResult((int) range.getOffset())
                    .setMaxResults((int) range.getSize())
                    .getResultList();
        } else {
            products = entityManager.createQuery(q).getResultList();
        }

        return fluently.assemble(products).toListOf(ProductRepresentation.class);
    }

    @Override
    protected long computeFullRequestSize(String tagName) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> q = cb.createQuery(Long.class);
        Root<Product> root = q.from(Product.class);
        q.select(cb.count(root))
                .where(cb.isMember(tagName, root.<List<String>>get("tags")));

        return entityManager.createQuery(q).getSingleResult();
    }
}
