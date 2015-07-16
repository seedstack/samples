package org.seedstack.samples.catalog.rest;


import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.seedstack.business.api.interfaces.assembler.ModelMapperAssembler;
import org.seedstack.samples.catalog.domain.Product;
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
                AbstractConverter<Object, Object> self = new AbstractConverter<Object, Object>() {
                    @Override
                    protected Object convert(Object source) {
                        return relRegistry.uri(CatalogResources.PRODUCT).set("title", source).expand();
                    }
                };

                map(source.getPricing().getAmount()).setAmount(0f);
                map(source.getPricing().getCurrency()).setCurrency(null);
                using(self).map(source.getName()).self(null);
            }
        });
    }

    @Override
    protected void configureMerge(ModelMapper modelMapper) {

    }
}
