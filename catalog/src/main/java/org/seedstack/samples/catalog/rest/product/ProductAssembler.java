package org.seedstack.samples.catalog.rest.product;


import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.seedstack.business.api.interfaces.assembler.ModelMapperAssembler;
import org.seedstack.samples.catalog.domain.product.Product;
import org.seedstack.samples.catalog.rest.CatalogRels;
import org.seedstack.seed.rest.api.RelRegistry;

import javax.inject.Inject;

/**
 * @author pierre.thirouin@ext.mpsa.com (Pierre Thirouin)
 */
public class ProductAssembler extends ModelMapperAssembler<Product, ProductRepresentation> {

    @Inject
    private RelRegistry relRegistry;

    @Override
    protected void configureAssembly(final ModelMapper modelMapper) {
        modelMapper.addMappings(new PropertyMap<Product, ProductRepresentation>() {
            @Override
            protected void configure() {
                AbstractConverter<Object, Object> selfConverter = new AbstractConverter<Object, Object>() {
                    @Override
                    protected Object convert(Object source) {
                        return relRegistry.uri(CatalogRels.PRODUCT).set("title", source).expand();
                    }
                };
                AbstractConverter<Object, Object> tagConverter = new AbstractConverter<Object, Object>() {
                    @Override
                    protected Object convert(Object source) {
                        return relRegistry.uri(CatalogRels.PRODUCT_TAGS).set("title", source).expand();
                    }
                };

                using(selfConverter).map().self(source.getName());
                using(tagConverter).map().setTags(source.getName());
            }
        });
    }

    @Override
    protected void configureMerge(ModelMapper modelMapper) {

    }
}
