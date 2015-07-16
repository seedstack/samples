package org.seedstack.samples.catalog.domain;

import org.seedstack.business.api.domain.BaseValueObject;

import javax.persistence.Embeddable;

/**
 * @author pierre.thirouin@ext.mpsa.com (Pierre Thirouin)
 */
@Embeddable
public class Tag extends BaseValueObject {

    private String name;

    Tag() {
    }

    public Tag(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
