/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.ddd.application.services.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.seedstack.business.domain.AggregateNotFoundException;
import org.seedstack.business.domain.Factory;
import org.seedstack.business.domain.Repository;
import org.seedstack.samples.ddd.application.services.BookingService;
import org.seedstack.samples.ddd.domain.model.cargo.Cargo;
import org.seedstack.samples.ddd.domain.model.cargo.Itinerary;
import org.seedstack.samples.ddd.domain.model.cargo.RouteSpecification;
import org.seedstack.samples.ddd.domain.model.cargo.TrackingId;
import org.seedstack.samples.ddd.domain.model.location.Location;
import org.seedstack.samples.ddd.domain.model.location.UnLocode;
import org.seedstack.samples.ddd.domain.services.RoutingService;
import org.seedstack.seed.transaction.Transactional;

public class BookingServiceImpl implements BookingService {
    private final Factory<Cargo> cargoFactory;
    private final Repository<Cargo, TrackingId> cargoRepository;
    private final Repository<Location, UnLocode> locationRepository;
    private final RoutingService routingService;
    private final Log logger = LogFactory.getLog(getClass());

    @Inject
    public BookingServiceImpl(final Factory<Cargo> cargoFactory,
            final Repository<Cargo, TrackingId> cargoRepository,
            final Repository<Location, UnLocode> locationRepository,
            final RoutingService routingService) {
        this.cargoFactory = cargoFactory;
        this.cargoRepository = cargoRepository;
        this.locationRepository = locationRepository;
        this.routingService = routingService;
    }

    @Override
    @Transactional
    public TrackingId bookNewCargo(
            final UnLocode originUnLocode,
            final UnLocode destinationUnLocode,
            final Date arrivalDeadline) {
        final RouteSpecification routeSpecification = new RouteSpecification(originUnLocode,
                destinationUnLocode,
                arrivalDeadline);
        final Cargo cargo = cargoFactory.create(routeSpecification);
        cargoRepository.add(cargo);
        logger.info("Booked new cargo with tracking id " + cargo.trackingId().id());
        return cargo.trackingId();
    }

    @Override
    @Transactional
    public List<Itinerary> requestPossibleRoutesForCargo(final TrackingId trackingId) {
        return cargoRepository.get(trackingId)
                .map(cargo -> routingService.fetchRoutesForSpecification(cargo.routeSpecification()))
                .orElse(Collections.emptyList());
    }

    @Override
    @Transactional
    public void assignCargoToRoute(final Itinerary itinerary, final TrackingId trackingId) {
        final Cargo cargo = cargoRepository.get(trackingId)
                .orElseThrow(() -> new IllegalArgumentException("Can't assign itinerary to non-existing cargo "
                        + trackingId));

        cargo.assignToRoute(itinerary);
        cargoRepository.add(cargo);
        logger.info("Assigned cargo " + trackingId + " to new route");
    }

    @Override
    @Transactional
    public void changeDestination(final TrackingId trackingId,
            final UnLocode unLocode) {
        final Cargo cargo = cargoRepository.get(trackingId)
                .orElseThrow(() -> new AggregateNotFoundException("No cargo found with tracking id " + trackingId));

        final RouteSpecification routeSpecification = new RouteSpecification(
                cargo.origin(), unLocode, cargo.routeSpecification().arrivalDeadline()
        );
        cargo.specifyNewRoute(routeSpecification);

        cargoRepository.add(cargo);
        logger.info("Changed destination for cargo " + trackingId + " to " + routeSpecification.destination());
    }
}
