package org.seedstack.samples.catalog.rest.tags;

import org.seedstack.business.api.interfaces.finder.Range;
import org.seedstack.business.api.interfaces.finder.Result;
import org.seedstack.business.api.interfaces.view.Page;
import org.seedstack.business.api.interfaces.view.PaginatedView;
import org.seedstack.samples.catalog.infrastructure.Config;
import org.seedstack.samples.catalog.rest.CatalogRels;
import org.seedstack.samples.catalog.rest.product.ProductRepresentation;
import org.seedstack.seed.persistence.jpa.api.JpaUnit;
import org.seedstack.seed.rest.api.Rel;
import org.seedstack.seed.rest.api.RelRegistry;
import org.seedstack.seed.rest.api.hal.HalRepresentation;
import org.seedstack.seed.transaction.api.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

/**
 * @author pierre.thirouin@ext.mpsa.com (Pierre Thirouin)
 */
@Rel(CatalogRels.TAG)
@Path("/tags/{tagName}")
public class TagResource {

    @Inject
    private RelRegistry relRegistry;

    @Inject @Named("tag")
    private TagFinder tagFinder;

    @JpaUnit(Config.JPA_UNIT)
    @Transactional
    @GET
    @Produces({MediaType.APPLICATION_JSON, "application/hal+json"})
    public Response getTag(@PathParam("tagName") String tagName, @DefaultValue("0") @QueryParam("pageIndex") Integer pageIndex,
                           @DefaultValue("10") @QueryParam("pageSize") Integer pageSize) {
        Map<String, Object> criteria = new HashMap<String, Object>();
        criteria.put("tagName", tagName);
        Page page = new Page(pageIndex, pageSize);

        Result<ProductRepresentation> productRepresentations = tagFinder.find(Range.rangeFromPageInfo(pageIndex, pageSize), criteria);
        PaginatedView<ProductRepresentation> view = new PaginatedView<ProductRepresentation>(productRepresentations, page);

        HalRepresentation tagRepresentation = new TagRepresentation(tagName, productRepresentations, page)
                .self(tagHref(tagName, pageIndex, pageSize));

        if (view.hasPrev()) {
            tagRepresentation.link("prev", tagHref(tagName, view.prev().getIndex(), pageSize));
        }
        if (view.hasNext()) {
            tagRepresentation.link("prev", tagHref(tagName, view.next().getIndex(), pageSize));
        }

        return Response.ok(tagRepresentation).build();
    }

    private String tagHref(String tagName, long pageIndex, long pageSize) {
        return relRegistry.uri(CatalogRels.TAG)
                .set("tagName", tagName)
                .set("pageIndex", pageIndex)
                .set("pageSize", pageSize).expand();
    }
}
