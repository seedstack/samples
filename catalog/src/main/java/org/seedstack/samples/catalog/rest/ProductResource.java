package org.seedstack.samples.catalog.rest;

import com.google.inject.Inject;
import org.seedstack.business.api.domain.Repository;
import org.seedstack.business.api.interfaces.assembler.FluentAssembler;
import org.seedstack.samples.catalog.domain.Product;
import org.seedstack.seed.persistence.jpa.api.Jpa;
import org.seedstack.seed.rest.api.Rel;
import org.seedstack.seed.rest.api.RelRegistry;
import org.seedstack.seed.rest.api.hal.HalBuilder;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 * @author pierre.thirouin@ext.mpsa.com (Pierre Thirouin)
 */
@Path("/products/{title}")
public class ProductResource {

    @Inject @Jpa
    private Repository<Product, String> repository;

    @Inject
    private RelRegistry relRegistry;

    @Inject
    private FluentAssembler fluently;

    @GET
    @Rel(CatalogResources.PRODUCT)
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("title") String title) {
        Product product = repository.load(title);
        if (product == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        ProductRepresentation representation = fluently.assemble(product).to(ProductRepresentation.class);

        return Response.ok(HalBuilder.create(representation)
                .self(relRegistry.uri(CatalogResources.PRODUCT).set("title", title).expand())).build();
    }
}
