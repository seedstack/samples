/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.catalog.interfaces.rest.tags;

import static org.seedstack.samples.catalog.interfaces.rest.PageInfo.PAGE_INDEX;
import static org.seedstack.samples.catalog.interfaces.rest.PageInfo.PAGE_SIZE;

import io.swagger.annotations.Api;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.Valid;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
@Rel(CatalogRels.TAG)
@Path("/tags/{tagName}")
public class TagResource {

    @Inject
    private RelRegistry relRegistry;

    @Inject
    @Named("tag")
    private TagFinder tagFinder;

    @GET
    @Produces({MediaType.APPLICATION_JSON, "application/hal+json"})
    public HalRepresentation getTag(@PathParam("tagName") String tagName, @Valid @BeanParam PageInfo pageInfo) {
        PaginatedView<ProductRepresentation> productView = tagFinder.findProductsByTag(pageInfo.page(), tagName);
        String selfLink = buildTagURI(tagName, pageInfo.page());
        HalRepresentation tagRepresentation = new TagRepresentation(tagName, productView).self(selfLink);
        if (productView.hasPrev()) {
            tagRepresentation.link("prev", buildTagURI(tagName, productView.prev()));
        }
        if (productView.hasNext()) {
            tagRepresentation.link("next", buildTagURI(tagName, productView.next()));
        }
        return tagRepresentation;
    }

    private String buildTagURI(String tagName, Page page) {
        return relRegistry.uri(CatalogRels.TAG)
                .set("tagName", tagName)
                .set(PAGE_INDEX, page.getIndex())
                .set(PAGE_SIZE, page.getCapacity()).getHref();
    }
}
