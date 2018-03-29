/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.catalog.interfaces.rest.catalog;

import static org.seedstack.samples.catalog.interfaces.rest.PageInfo.PAGE_INDEX;
import static org.seedstack.samples.catalog.interfaces.rest.PageInfo.PAGE_SIZE;

import io.swagger.annotations.Api;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.seedstack.business.view.Page;
import org.seedstack.business.view.PaginatedView;
import org.seedstack.samples.catalog.interfaces.rest.CatalogRels;
import org.seedstack.samples.catalog.interfaces.rest.PageInfo;
import org.seedstack.samples.catalog.interfaces.rest.product.ProductRepresentation;
import org.seedstack.seed.rest.Rel;
import org.seedstack.seed.rest.RelRegistry;
import org.seedstack.seed.rest.hal.HalRepresentation;

@Api
@Path("/products")
public class ProductsResource {

    @Inject
    private ProductsFinder productsFinder;

    @Inject
    private RelRegistry relRegistry;

    @GET
    @Rel(value = CatalogRels.CATALOG, home = true)
    @Produces({MediaType.APPLICATION_JSON, "application/hal+json"})
    public HalRepresentation products(@QueryParam("q") String filter, @Valid @BeanParam PageInfo pageInfo) {
        PaginatedView<ProductRepresentation> view = productsFinder.findProducts(pageInfo.page(), filter);
        return buildHalRepresentation(pageInfo, view);
    }

    private HalRepresentation buildHalRepresentation(@BeanParam PageInfo pageInfo,
            PaginatedView<ProductRepresentation> view) {
        HalRepresentation representation = new ProductsRepresentation(view)
                .self(relRegistry.uri(CatalogRels.CATALOG)
                        .set(PAGE_INDEX, pageInfo.pageIndex)
                        .set(PAGE_SIZE, pageInfo.pageSize).getHref());

        if (view.hasNext()) {
            addPageLink(representation, "next", view.next());
        }
        if (view.hasPrev()) {
            addPageLink(representation, "prev", view.prev());
        }
        return representation;
    }

    private void addPageLink(HalRepresentation representation, String link, Page page) {
        representation.link(link, relRegistry.uri(CatalogRels.CATALOG)
                .set(PAGE_INDEX, page.getIndex()).set(PAGE_SIZE, page.getCapacity()).getHref());
    }
}
