package org.seedstack.samples.catalog.domain.product;

import org.seedstack.business.api.domain.BaseValueObject;

/**
 * @author pierre.thirouin@ext.mpsa.com (Pierre Thirouin)
 */

public class Tag extends BaseValueObject {

    private String name;

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
