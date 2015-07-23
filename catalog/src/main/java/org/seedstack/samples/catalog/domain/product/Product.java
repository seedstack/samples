package org.seedstack.samples.catalog.domain.product;

import org.hibernate.annotations.Type;
import org.seedstack.business.api.domain.BaseAggregateRoot;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.net.URI;
import java.util.Set;

/**
 * @author pierre.thirouin@ext.mpsa.com (Pierre Thirouin)
 */
@Entity
public class Product extends BaseAggregateRoot<String> {

    @Id
    private String name;

    private URI picture;

    private Price pricing;

    @Type(type = "text")
    private String description;

    @ElementCollection
    private Set<String> tags;

    Product() {
    }

    public Product(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public URI getPicture() {
        return picture;
    }

    public void setPicture(URI picture) {
        this.picture = picture;
    }

    public Price getPricing() {
        return pricing;
    }

    public void setPricing(Price pricing) {
        this.pricing = pricing;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void tag(String tag) {
        this.tags.add(tag);
    }

    @Override
    public String getEntityId() {
        return this.name;
    }
}
