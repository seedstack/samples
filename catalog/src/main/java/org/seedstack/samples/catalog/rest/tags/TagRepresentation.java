package org.seedstack.samples.catalog.rest.tags;

import org.seedstack.business.api.interfaces.finder.Result;
import org.seedstack.business.api.interfaces.view.Page;
import org.seedstack.samples.catalog.rest.product.ProductRepresentation;
import org.seedstack.seed.rest.api.hal.HalRepresentation;

/**
 * @author pierre.thirouin@ext.mpsa.com (Pierre Thirouin)
 */
public class TagRepresentation extends HalRepresentation {

    private String name;

    private long totalItems;

    private long currentPage;

    protected TagRepresentation() {
    }

    public TagRepresentation(String tagName, Result<ProductRepresentation> result, Page page) {
        embedded("products", result.getResult());
        this.name = tagName;
        this.totalItems = result.getFullSize();
        this.currentPage = page.getIndex();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTotalItems() {
        return totalItems;
    }

    public long getCurrentPage() {
        return currentPage;
    }
}
