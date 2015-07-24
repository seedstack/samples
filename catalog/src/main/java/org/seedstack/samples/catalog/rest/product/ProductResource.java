package org.seedstack.samples.catalog.rest.product;

import com.google.inject.Inject;
import com.sun.jersey.api.NotFoundException;
import org.seedstack.business.api.domain.Repository;
import org.seedstack.business.api.interfaces.assembler.FluentAssembler;
import org.seedstack.samples.catalog.domain.product.Product;
import org.seedstack.samples.catalog.infrastructure.Config;
import org.seedstack.samples.catalog.rest.CatalogRels;
import org.seedstack.samples.catalog.rest.catalog.TagRepresentation;
import org.seedstack.seed.persistence.jpa.api.Jpa;
import org.seedstack.seed.persistence.jpa.api.JpaUnit;
import org.seedstack.seed.rest.api.Rel;
import org.seedstack.seed.rest.api.RelRegistry;
import org.seedstack.seed.rest.api.hal.HalBuilder;
import org.seedstack.seed.rest.api.hal.HalRepresentation;
import org.seedstack.seed.transaction.api.Transactional;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;


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
    @Transactional
    @JpaUnit(Config.JPA_UNIT)
    @Rel(value = CatalogRels.PRODUCT, home = true)
    @Produces({MediaType.APPLICATION_JSON, "application/hal+json"})
    public Response getProduct(@PathParam("title") String title) {
        Product product = repository.load(title);
        if (product == null) {
            throw new NotFoundException("Product " + title + " doesn't exist");
        }

        return Response.ok(fluently.assemble(product).to(ProductRepresentation.class)).build();
    }

    @Transactional
    @JpaUnit(Config.JPA_UNIT)
    @GET
    @Rel(CatalogRels.PRODUCT_TAGS)
    @Path("/tags")
    @Produces({MediaType.APPLICATION_JSON, "application/hal+json"})
    public Response getTags(@PathParam("title") String title) {
        Product product = repository.load(title);
        if (product == null) {
            throw new NotFoundException("Product " + title + " doesn't exist");
        }

        List<HalRepresentation> tagRepresentations = new ArrayList<HalRepresentation>(product.getTags().size());
        for (String tagName : product.getTags()) {
            tagRepresentations.add(HalBuilder.create(new TagRepresentation(tagName)).self(relRegistry.uri(CatalogRels.TAG).set("tagName", tagName).expand()));
        }

        return Response.ok(HalBuilder.create(null)
                .self(relRegistry.uri(CatalogRels.PRODUCT).set("title", title).expand())
                .embedded("tags", tagRepresentations)
        ).build();
    }
}
