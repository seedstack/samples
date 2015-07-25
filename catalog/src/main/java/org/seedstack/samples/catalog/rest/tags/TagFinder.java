package org.seedstack.samples.catalog.rest.tags;

import org.seedstack.business.api.interfaces.finder.Finder;
import org.seedstack.business.api.interfaces.finder.RangeFinder;
import org.seedstack.samples.catalog.rest.product.ProductRepresentation;

import java.util.Map;

/**
 * @author pierre.thirouin@ext.mpsa.com (Pierre Thirouin)
 */
@Finder
public interface TagFinder extends RangeFinder<ProductRepresentation, Map<String, Object>> {

}
