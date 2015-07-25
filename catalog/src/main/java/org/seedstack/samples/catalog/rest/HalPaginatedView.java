package org.seedstack.samples.catalog.rest;

import org.seedstack.business.api.interfaces.finder.Result;
import org.seedstack.business.api.interfaces.view.Page;
import org.seedstack.business.api.interfaces.view.PaginatedView;
import org.seedstack.seed.rest.api.hal.HalRepresentation;

/**
 * @author pierre.thirouin@ext.mpsa.com (Pierre Thirouin)
 */
public class HalPaginatedView<Item> extends HalRepresentation {

    private long totalItems;

    private long currentPage;

    protected HalPaginatedView() {
    }

    public HalPaginatedView(Result<Item> result, Page page, String itemRel) {
        PaginatedView<Item> view = new PaginatedView<Item>(result, page);
        this.totalItems = view.getResultSize();
        this.currentPage = view.getPageIndex();
        embedded(itemRel, view.getView());
    }

    public HalPaginatedView(Result<Item> result, Page page) {
        this(result, page, "items");
    }

    public long getTotalItems() {
        return totalItems;
    }

    public long getCurrentPage() {
        return currentPage;
    }
}
