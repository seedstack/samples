package org.seedstack.samples.catalog.rest;

import org.seedstack.business.api.interfaces.finder.GenericFinder;

import java.util.Map;

/**
 * @author pierre.thirouin@ext.mpsa.com (Pierre Thirouin)
 */
public interface ProductsFinder extends GenericFinder<ProductRepresentation, Map<String, Object>> {
}
