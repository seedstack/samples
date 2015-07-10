package org.seedstack.samples.catalog.rest;

import org.seedstack.business.api.interfaces.finder.Range;
import org.seedstack.business.api.interfaces.finder.Result;
import org.seedstack.business.api.interfaces.view.Page;
import org.seedstack.business.api.interfaces.view.PaginatedView;
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
    @Rel(CatalogResources.CATALOG)
    @Produces({"application/hal+json", MediaType.APPLICATION_JSON})
    public Response products(@DefaultValue("0") @QueryParam("pageIndex") Integer pageIndex,
                             @DefaultValue("10") @QueryParam("pageSize") Integer pageSize) {
        Range range = new Range(0, pageSize);

        Result<ProductRepresentation> result = productsFinder.find(range, null);

        if (result.getSize() > 0) {
            Page page = new Page(pageIndex, pageSize);
            PaginatedView<ProductRepresentation> view = new PaginatedView<ProductRepresentation>(result, page);
            HalRepresentation representation = new ProductsRepresentation(view);

            String href = relRegistry.uri(CatalogResources.CATALOG)
                    .set("pageIndex", pageIndex).set("pageSize", pageSize).expand();

            return Response.ok(representation.self(href)).build();
        } else {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
    }
}
