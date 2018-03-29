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
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
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
import org.seedstack.samples.catalog.interfaces.rest.catalog.ProductsFinder;
import org.seedstack.samples.catalog.interfaces.rest.product.ProductRepresentation;
import org.seedstack.seed.transaction.Transactional;

@Transactional
@JpaUnit(Config.JPA_UNIT)
class ProductsJpaFinder extends BaseRangeFinder<ProductRepresentation, String> implements ProductsFinder {
    @Inject
    private EntityManager entityManager;

    @Inject
    private FluentAssembler fluently;

    @Override
    public PaginatedView<ProductRepresentation> findProducts(Page page, String query) {
        Result<ProductRepresentation> result = find(Range.rangeFromPageInfo(page.getIndex(), page.getCapacity()),
                query);
        return new PaginatedView<>(result, page);
    }

    private String buildSqlLikeQuery(String query) {
        return "%" + query + "%";
    }

    @Override
    protected List<ProductRepresentation> computeResultList(Range range, String criteria) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> q = cb.createQuery(Product.class);
        Root<Product> product = q.from(Product.class);

        q.select(product);
        if (criteria != null) {
            q.where(cb.like(product.<String>get("name"), buildSqlLikeQuery(criteria)));
        }

        TypedQuery<Product> query = entityManager.createQuery(q);
        if (range != null) {
            query.setFirstResult((int) range.getOffset()).setMaxResults((int) range.getSize());
        }

        return fluently.assemble(query.getResultList()).toListOf(ProductRepresentation.class);
    }

    @Override
    protected long computeFullRequestSize(String criteria) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> q = cb.createQuery(Long.class);
        Root<Product> product = q.from(Product.class);
        q.select(cb.count(product));
        if (criteria != null) {
            q.where(cb.like(product.<String>get("name"), buildSqlLikeQuery(criteria)));
        }
        return entityManager.createQuery(q).getSingleResult();
    }
}
