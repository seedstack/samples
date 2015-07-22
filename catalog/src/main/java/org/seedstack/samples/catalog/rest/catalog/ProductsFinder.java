package org.seedstack.samples.catalog.rest.catalog;

import org.seedstack.business.api.interfaces.finder.Finder;
import org.seedstack.business.api.interfaces.finder.RangeFinder;
import org.seedstack.samples.catalog.rest.product.ProductRepresentation;

import java.util.Map;

/**
 * @author pierre.thirouin@ext.mpsa.com (Pierre Thirouin)
 */
@Finder
public interface ProductsFinder extends RangeFinder<ProductRepresentation, Map<String, Object>> {
}
