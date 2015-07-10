package org.seedstack.samples.catalog.rest;

import org.seedstack.business.api.interfaces.view.PaginatedView;
import org.seedstack.seed.rest.api.hal.HalRepresentation;

/**
 * @author pierre.thirouin@ext.mpsa.com (Pierre Thirouin)
 */
public class ProductsRepresentation extends HalRepresentation {

    private long totalProduct;

    private long currentPage;

    ProductsRepresentation() {
    }

    public ProductsRepresentation(PaginatedView<ProductRepresentation> page) {
        this.totalProduct = page.getResultSize();
        this.currentPage = page.getPageIndex();
        embedded("products", page.getView());
    }

    public long getTotalProduct() {
        return totalProduct;
    }

    public long getCurrentPage() {
        return currentPage;
    }
}
