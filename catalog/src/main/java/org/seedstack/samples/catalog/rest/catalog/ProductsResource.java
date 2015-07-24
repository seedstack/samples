package org.seedstack.samples.catalog.rest.catalog;

import org.seedstack.business.api.interfaces.finder.Range;
import org.seedstack.business.api.interfaces.finder.Result;
import org.seedstack.business.api.interfaces.view.Page;
import org.seedstack.business.api.interfaces.view.PaginatedView;
import org.seedstack.samples.catalog.rest.CatalogRels;
import org.seedstack.samples.catalog.rest.product.ProductRepresentation;
import org.seedstack.seed.rest.api.Rel;
import org.seedstack.seed.rest.api.RelRegistry;
import org.seedstack.seed.rest.api.hal.HalRepresentation;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author pierre.thirouin@ext.mpsa.com (Pierre Thirouin)
 */
@Path("/products")
public class ProductsResource {

    @Inject
    private ProductsFinder productsFinder;

    @Inject
    private RelRegistry relRegistry;

    @GET
    @Rel(value = CatalogRels.CATALOG, home = true)
    @Produces({MediaType.APPLICATION_JSON, "application/hal+json"})
    public Response products(@DefaultValue("0") @QueryParam("pageIndex") Integer pageIndex,
                             @DefaultValue("10") @QueryParam("pageSize") Integer pageSize) {

        Result<ProductRepresentation> result = productsFinder.find(Range.rangeFromPageInfo(pageIndex, pageSize), null);

        if (result.getSize() > 0) {
            Page page = new Page(pageIndex, pageSize);
            PaginatedView<ProductRepresentation> view = new PaginatedView<ProductRepresentation>(result, page);

            HalRepresentation representation = new ProductsRepresentation(view);

            representation.self(relRegistry.uri(CatalogRels.CATALOG)
                    .set("pageIndex", pageIndex).set("pageSize", pageSize).expand());

            if (view.hasNext()) {
                Page next = view.next();
                representation.link("next", relRegistry.uri(CatalogRels.CATALOG)
                        .set("pageIndex", next.getIndex()).set("pageSize", next.getCapacity()).expand());
            }

            if (view.hasPrev()) {
                Page prev = view.prev();
                representation.link("prev", relRegistry.uri(CatalogRels.CATALOG)
                        .set("pageIndex", prev.getIndex()).set("pageSize", prev.getCapacity()).expand());
            }

            return Response.ok(representation).build();
        } else {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
    }
}
