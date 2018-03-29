/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.ddd.infrastructure.routing;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.inject.Inject;
import org.seedstack.samples.ddd.domain.model.cargo.Itinerary;
import org.seedstack.samples.ddd.domain.model.cargo.Leg;
import org.seedstack.samples.ddd.domain.model.cargo.RouteSpecification;
import org.seedstack.samples.ddd.domain.model.location.UnLocode;
import org.seedstack.samples.ddd.domain.model.voyage.VoyageNumber;
import org.seedstack.samples.ddd.domain.services.RoutingService;
import org.seedstack.samples.ddd.infrastructure.pathfinder.GraphTraversalService;
import org.seedstack.samples.ddd.infrastructure.pathfinder.TransitEdge;
import org.seedstack.samples.ddd.infrastructure.pathfinder.TransitPath;
import org.seedstack.seed.Logging;
import org.slf4j.Logger;

/**
 * Our end of the routing service. This is basically a data model
 * translation layer between our domain model and the API put forward
 * by the routing team, which operates in a different context from us.
 */
public class ExternalRoutingService implements RoutingService {
    @Logging
    private Logger logger;
    @Inject
    private GraphTraversalService graphTraversalService;

    public List<Itinerary> fetchRoutesForSpecification(RouteSpecification routeSpecification) {
        /*
          The RouteSpecification is picked apart and adapted to the external API.
         */
        final UnLocode origin = routeSpecification.origin();
        final UnLocode destination = routeSpecification.destination();
        final Properties limitations = new Properties();
        limitations.setProperty("DEADLINE", routeSpecification.arrivalDeadline().toString());
        final List<TransitPath> transitPaths;
        transitPaths = graphTraversalService.findShortestPath(
                origin.idString(),
                destination.idString(),
                limitations
        );

        /*
         The returned result is then translated back into our domain model.
        */
        final List<Itinerary> itineraries = new ArrayList<>();
        for (TransitPath transitPath : transitPaths) {
            final Itinerary itinerary = toItinerary(transitPath);
            // Use the specification to safe-guard against invalid itineraries
            if (routeSpecification.isSatisfiedBy(itinerary)) {
                itineraries.add(itinerary);
            } else {
                logger.warn("Received itinerary that did not satisfy the route specification");
            }
        }

        return itineraries;
    }

    private Itinerary toItinerary(TransitPath transitPath) {
        List<Leg> legs = new ArrayList<>(transitPath.getTransitEdges().size());
        for (TransitEdge edge : transitPath.getTransitEdges()) {
            legs.add(toLeg(edge));
        }
        return new Itinerary(legs);
    }

    private Leg toLeg(TransitEdge edge) {
        return new Leg(new VoyageNumber(edge.getEdge()),
                new UnLocode(edge.getFromNode()),
                new UnLocode(edge.getToNode()),
                edge.getFromDate(),
                edge.getToDate()
        );
    }
}
