package org.seedstack.samples.catalog.rest;

import org.seedstack.business.api.interfaces.assembler.DtoOf;
import org.seedstack.samples.catalog.domain.Product;
import org.seedstack.seed.rest.api.hal.HalRepresentation;

import java.net.URI;
import java.util.List;

/**
 * @author pierre.thirouin@ext.mpsa.com (Pierre Thirouin)
 */
@DtoOf(Product.class)
public class ProductRepresentation extends HalRepresentation {

    private String name;

    private URI picture;

    private float amount;

    private String currency;

    private String description;

    private List<String> tags;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public URI getPicture() {
        return picture;
    }

    public void setPicture(URI picture) {
        this.picture = picture;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
