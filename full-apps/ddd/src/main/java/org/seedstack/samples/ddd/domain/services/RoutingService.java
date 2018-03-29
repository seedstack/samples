/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.ddd.domain.services;

import java.util.List;
import org.seedstack.business.Service;
import org.seedstack.samples.ddd.domain.model.cargo.Itinerary;
import org.seedstack.samples.ddd.domain.model.cargo.RouteSpecification;

/**
 * Routing service.
 */
@Service
public interface RoutingService {

    /**
     * @param routeSpecification route specification
     * @return A list of itineraries that satisfy the specification. May be an empty list if no route is found.
     */
    List<Itinerary> fetchRoutesForSpecification(RouteSpecification routeSpecification);

}
