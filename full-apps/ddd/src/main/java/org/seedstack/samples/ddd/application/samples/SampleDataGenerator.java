/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.ddd.application.samples;

import static java.util.Arrays.asList;
import static org.seedstack.samples.ddd.application.samples.SampleLocations.DALLAS;
import static org.seedstack.samples.ddd.application.samples.SampleLocations.HANGZOU;
import static org.seedstack.samples.ddd.application.samples.SampleLocations.HELSINKI;
import static org.seedstack.samples.ddd.application.samples.SampleLocations.HONGKONG;
import static org.seedstack.samples.ddd.application.samples.SampleLocations.NEWYORK;
import static org.seedstack.samples.ddd.application.samples.SampleLocations.STOCKHOLM;
import static org.seedstack.samples.ddd.application.samples.SampleVoyages.DALLAS_TO_HELSINKI;
import static org.seedstack.samples.ddd.application.samples.SampleVoyages.DALLAS_TO_HELSINKI_ALT;
import static org.seedstack.samples.ddd.application.samples.SampleVoyages.HELSINKI_TO_HONGKONG;
import static org.seedstack.samples.ddd.application.samples.SampleVoyages.HONGKONG_TO_NEW_YORK;
import static org.seedstack.samples.ddd.application.samples.SampleVoyages.NEW_YORK_TO_DALLAS;
import static org.seedstack.samples.ddd.application.util.DateTestUtil.toDate;

import com.google.common.collect.Lists;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import javax.inject.Inject;
import org.seedstack.business.domain.Repository;
import org.seedstack.samples.ddd.domain.model.cargo.Cargo;
import org.seedstack.samples.ddd.domain.model.cargo.Itinerary;
import org.seedstack.samples.ddd.domain.model.cargo.Leg;
import org.seedstack.samples.ddd.domain.model.cargo.RouteSpecification;
import org.seedstack.samples.ddd.domain.model.cargo.TrackingId;
import org.seedstack.samples.ddd.domain.model.handling.CannotCreateHandlingEventException;
import org.seedstack.samples.ddd.domain.model.handling.HandlingEvent;
import org.seedstack.samples.ddd.domain.model.handling.HandlingEventFactory;
import org.seedstack.samples.ddd.domain.model.handling.HandlingEventRepository;
import org.seedstack.samples.ddd.domain.model.handling.HandlingHistory;
import org.seedstack.samples.ddd.domain.model.location.Location;
import org.seedstack.samples.ddd.domain.model.location.UnLocode;
import org.seedstack.samples.ddd.domain.model.voyage.CarrierMovement;
import org.seedstack.samples.ddd.domain.model.voyage.Schedule;
import org.seedstack.samples.ddd.domain.model.voyage.Voyage;
import org.seedstack.samples.ddd.domain.model.voyage.VoyageNumber;
import org.seedstack.seed.LifecycleListener;
import org.seedstack.seed.Logging;
import org.slf4j.Logger;

/**
 * Provides sample data.
 */
public class SampleDataGenerator implements LifecycleListener {
    private static final Timestamp base;

    static {
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2008-01-01");
            base = new Timestamp(date.getTime() - 1000L * 60 * 60 * 24 * 100);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private final Repository<Cargo, TrackingId> cargoRepository;
    private final Repository<Voyage, VoyageNumber> voyageRepository;
    private final Repository<Location, UnLocode> locationRepository;
    private final HandlingEventRepository handlingEventRepository;
    private final HandlingEventFactory handlingEventFactory;
    @Logging
    private Logger logger;

    @Inject
    public SampleDataGenerator(
            Repository<Cargo, TrackingId> cargoRepository,
            Repository<Voyage, VoyageNumber> voyageRepository,
            Repository<Location, UnLocode> locationRepository,
            HandlingEventRepository handlingEventRepository,
            HandlingEventFactory handlingEventFactory) {
        this.cargoRepository = cargoRepository;
        this.voyageRepository = voyageRepository;
        this.locationRepository = locationRepository;
        this.handlingEventRepository = handlingEventRepository;
        this.handlingEventFactory = handlingEventFactory;
    }

    private static Timestamp ts(int hours) {
        return new Timestamp(base.getTime() + 1000L * 60 * 60 * hours);
    }

    @Override
    public void started() {
        logger.info("*** Loading location data ***");
        locationRepository.clear();
        loadLocationData();

        logger.info("*** Loading cargo data ***");
        cargoRepository.clear();
        loadCargoData();

        logger.info("*** Loading voyage data ***");
        voyageRepository.clear();
        loadVoyageData();

        logger.info("*** Loading handling event data ***");
        handlingEventRepository.clear();
        loadHandlingEventData();

        logger.info("*** Loading additional sample data ***");
        loadSampleData();
    }

    @Override
    public void stopping() {

    }

    private void loadLocationData() {
        Location[] locations = {
                new Location(new UnLocode("SESTO"), "Stockholm"),
                new Location(new UnLocode("AUMEL"), "Melbourne"),
                new Location(new UnLocode("CNHKG"), "Hongkong"),
                new Location(new UnLocode("JPTOK"), "Tokyo"),
                new Location(new UnLocode("FIHEL"), "Helsinki"),
                new Location(new UnLocode("DEHAM"), "Hamburg"),
                new Location(new UnLocode("USCHI"), "Chicago")
        };

        Arrays.stream(locations).forEach(locationRepository::add);
    }

    private void loadCargoData() {
        Cargo cargo1 = new Cargo(new TrackingId("1"), new RouteSpecification(new UnLocode("SESTO"),
                new UnLocode("AUMEL"),
                ts(10)));
        Cargo cargo2 = new Cargo(new TrackingId("2"), new RouteSpecification(new UnLocode("SESTO"),
                new UnLocode("FIHEL"),
                ts(20)));
        Cargo cargo3 = new Cargo(new TrackingId("3"), new RouteSpecification(new UnLocode("AUMEL"),
                new UnLocode("SESTO"),
                ts(30)));
        Cargo cargo4 = new Cargo(new TrackingId("4"), new RouteSpecification(new UnLocode("FIHEL"),
                new UnLocode("SESTO"),
                ts(40)));
        Cargo cargo5 = new Cargo(new TrackingId("5"), new RouteSpecification(new UnLocode("CNHKG"),
                new UnLocode("FIHEL"),
                ts(50)));
        cargo5.assignToRoute(new Itinerary(Lists.newArrayList(
                new Leg(new VoyageNumber("0101"), new UnLocode("CNHKG"), new UnLocode("AUMEL"), ts(1), ts(2)),
                new Leg(new VoyageNumber("0101"), new UnLocode("AUMEL"), new UnLocode("SESTO"), ts(3), ts(4)),
                new Leg(new VoyageNumber("0101"), new UnLocode("SESTO"), new UnLocode("FIHEL"), ts(4), ts(5))
        )));
        Cargo cargo6 =
                new Cargo(new TrackingId("6"), new RouteSpecification(new UnLocode("DEHAM"),
                        new UnLocode("JPTOK"),
                        ts(60)));
        cargo6.assignToRoute(new Itinerary(Lists.newArrayList(
                new Leg(new VoyageNumber("0101"), new UnLocode("DEHAM"), new UnLocode("SESTO"), ts(1), ts(2)),
                new Leg(new VoyageNumber("0101"), new UnLocode("SESTO"), new UnLocode("USCHI"), ts(3), ts(4)),
                new Leg(new VoyageNumber("0101"), new UnLocode("USCHI"), new UnLocode("JPTOK"), ts(4), ts(5))
        )));

        Arrays.stream(new Cargo[]{
                cargo1,
                cargo2,
                cargo3,
                cargo4,
                cargo5,
                cargo6,
        }).forEach(cargoRepository::add);
    }

    private void loadVoyageData() {
        Voyage[] voyages = {
                new Voyage(new VoyageNumber("0101"), new Schedule(Lists.newArrayList(
                        new CarrierMovement(new UnLocode("SESTO"), new UnLocode("FIHEL"), ts(1), ts(2)),
                        new CarrierMovement(new UnLocode("FIHEL"), new UnLocode("DEHAM"), ts(1), ts(2)),
                        new CarrierMovement(new UnLocode("DEHAM"), new UnLocode("CNHKG"), ts(1), ts(2)),
                        new CarrierMovement(new UnLocode("CNHKG"), new UnLocode("JPTOK"), ts(1), ts(2)),
                        new CarrierMovement(new UnLocode("JPTOK"), new UnLocode("AUMEL"), ts(1), ts(2))
                ))),
                new Voyage(new VoyageNumber("0202"), new Schedule(Lists.newArrayList(
                        new CarrierMovement(new UnLocode("AUMEL"), new UnLocode("USCHI"), ts(1), ts(2)),
                        new CarrierMovement(new UnLocode("USCHI"), new UnLocode("DEHAM"), ts(1), ts(2)),
                        new CarrierMovement(new UnLocode("DEHAM"), new UnLocode("SESTO"), ts(1), ts(2)),
                        new CarrierMovement(new UnLocode("SESTO"), new UnLocode("FIHEL"), ts(1), ts(2))
                ))),
                new Voyage(new VoyageNumber("0303"), new Schedule(Lists.newArrayList(
                        new CarrierMovement(new UnLocode("CNHKG"), new UnLocode("AUMEL"), ts(1), ts(2)),
                        new CarrierMovement(new UnLocode("AUMEL"), new UnLocode("FIHEL"), ts(1), ts(2)),
                        new CarrierMovement(new UnLocode("FIHEL"), new UnLocode("DEHAM"), ts(1), ts(2)),
                        new CarrierMovement(new UnLocode("DEHAM"), new UnLocode("SESTO"), ts(1), ts(2)),
                        new CarrierMovement(new UnLocode("SESTO"), new UnLocode("USCHI"), ts(1), ts(2)),
                        new CarrierMovement(new UnLocode("USCHI"), new UnLocode("JPTKO"), ts(1), ts(2))
                )))
        };

        Arrays.stream(voyages).forEach(voyageRepository::add);
    }

    private void loadHandlingEventData() {
        HandlingEvent[] handlingEvents = {
                //XYZ (SESTO-FIHEL-DEHAM-CNHKG-JPTOK-AUMEL)
                new HandlingEvent(new TrackingId("1"),
                        ts(0),
                        ts((0)),
                        HandlingEvent.Type.RECEIVE,
                        new UnLocode("SESTO")),
                new HandlingEvent(new TrackingId("1"),
                        ts((4)),
                        ts((5)),
                        HandlingEvent.Type.LOAD,
                        new UnLocode("SESTO"),
                        new VoyageNumber("1")),
                new HandlingEvent(new TrackingId("1"),
                        ts((14)),
                        ts((14)),
                        HandlingEvent.Type.UNLOAD,
                        new UnLocode("FIHEL"),
                        new VoyageNumber("1")),
                new HandlingEvent(new TrackingId("1"),
                        ts((15)),
                        ts((15)),
                        HandlingEvent.Type.LOAD,
                        new UnLocode("FIHEL"),
                        new VoyageNumber("1")),
                new HandlingEvent(new TrackingId("1"),
                        ts((30)),
                        ts((30)),
                        HandlingEvent.Type.UNLOAD,
                        new UnLocode("DEHAM"),
                        new VoyageNumber("1")),
                new HandlingEvent(new TrackingId("1"),
                        ts((33)),
                        ts((33)),
                        HandlingEvent.Type.LOAD,
                        new UnLocode("DEHAM"),
                        new VoyageNumber("1")),
                new HandlingEvent(new TrackingId("1"),
                        ts((34)),
                        ts((34)),
                        HandlingEvent.Type.UNLOAD,
                        new UnLocode("CNHKG"),
                        new VoyageNumber("1")),
                new HandlingEvent(new TrackingId("1"),
                        ts((60)),
                        ts((60)),
                        HandlingEvent.Type.LOAD,
                        new UnLocode("CNHKG"),
                        new VoyageNumber("1")),
                new HandlingEvent(new TrackingId("1"),
                        ts((70)),
                        ts((71)),
                        HandlingEvent.Type.UNLOAD,
                        new UnLocode("JPTOK"),
                        new VoyageNumber("1")),
                new HandlingEvent(new TrackingId("1"),
                        ts((75)),
                        ts((75)),
                        HandlingEvent.Type.LOAD,
                        new UnLocode("JPTOK"),
                        new VoyageNumber("1")),
                new HandlingEvent(new TrackingId("1"),
                        ts((88)),
                        ts((88)),
                        HandlingEvent.Type.UNLOAD,
                        new UnLocode("AUMEL"),
                        new VoyageNumber("1")),
                new HandlingEvent(new TrackingId("1"),
                        ts((100)),
                        ts((102)),
                        HandlingEvent.Type.CLAIM,
                        new UnLocode("AUMEL")),

                //ZYX (AUMEL - USCHI - DEHAM -)
                new HandlingEvent(new TrackingId("3"),
                        ts((200)),
                        ts((201)),
                        HandlingEvent.Type.RECEIVE,
                        new UnLocode("AUMEL")),
                new HandlingEvent(new TrackingId("3"),
                        ts((202)),
                        ts((202)),
                        HandlingEvent.Type.LOAD,
                        new UnLocode("AUMEL"),
                        new VoyageNumber("2")),
                new HandlingEvent(new TrackingId("3"),
                        ts((208)),
                        ts((208)),
                        HandlingEvent.Type.UNLOAD,
                        new UnLocode("USCHI"),
                        new VoyageNumber("2")),
                new HandlingEvent(new TrackingId("3"),
                        ts((212)),
                        ts((212)),
                        HandlingEvent.Type.LOAD,
                        new UnLocode("USCHI"),
                        new VoyageNumber("2")),
                new HandlingEvent(new TrackingId("3"),
                        ts((230)),
                        ts((230)),
                        HandlingEvent.Type.UNLOAD,
                        new UnLocode("DEHAM"),
                        new VoyageNumber("2")),
                new HandlingEvent(new TrackingId("3"),
                        ts((235)),
                        ts((235)),
                        HandlingEvent.Type.LOAD,
                        new UnLocode("DEHAM"),
                        new VoyageNumber("2")),

                //ABC
                new HandlingEvent(new TrackingId("2"),
                        ts((20)),
                        ts((21)),
                        HandlingEvent.Type.CLAIM,
                        new UnLocode("AUMEL")),

                //CBA
                new HandlingEvent(new TrackingId("4"),
                        ts((0)),
                        ts((1)),
                        HandlingEvent.Type.RECEIVE,
                        new UnLocode("AUMEL")),
                new HandlingEvent(new TrackingId("4"),
                        ts((10)),
                        ts((11)),
                        HandlingEvent.Type.LOAD,
                        new UnLocode("AUMEL"),
                        new VoyageNumber("2")),
                new HandlingEvent(new TrackingId("4"),
                        ts((20)),
                        ts((21)),
                        HandlingEvent.Type.UNLOAD,
                        new UnLocode("USCHI"),
                        new VoyageNumber("2")),

                //FGH
                new HandlingEvent(new TrackingId("5"),
                        ts(100),
                        ts(160),
                        HandlingEvent.Type.RECEIVE,
                        new UnLocode("CNHKG")),
                new HandlingEvent(new TrackingId("5"),
                        ts(150),
                        ts(110),
                        HandlingEvent.Type.LOAD,
                        new UnLocode("CNHKG"),
                        new VoyageNumber("3")),

                // JKL
                new HandlingEvent(new TrackingId("6"),
                        ts(200),
                        ts(220),
                        HandlingEvent.Type.RECEIVE,
                        new UnLocode("DEHAM")),
                new HandlingEvent(new TrackingId("6"),
                        ts(300),
                        ts(330),
                        HandlingEvent.Type.LOAD,
                        new UnLocode("DEHAM"),
                        new VoyageNumber("3")),
                new HandlingEvent(new TrackingId("6"),
                        ts(400),
                        ts(440),
                        HandlingEvent.Type.UNLOAD,
                        new UnLocode("FIHEL"),
                        new VoyageNumber("3"))
        };

        Arrays.stream(handlingEvents).forEach(handlingEventRepository::add);
    }

    public void loadSampleData() {
        SampleLocations.getAll().forEach(locationRepository::add);

        voyageRepository.add(HONGKONG_TO_NEW_YORK);
        voyageRepository.add(NEW_YORK_TO_DALLAS);
        voyageRepository.add(DALLAS_TO_HELSINKI);
        voyageRepository.add(HELSINKI_TO_HONGKONG);
        voyageRepository.add(DALLAS_TO_HELSINKI_ALT);

        // Cargo ABC123

        TrackingId trackingId = new TrackingId("ABC123");
        Cargo abc123 = new Cargo(trackingId, new RouteSpecification(HONGKONG.code(),
                HELSINKI.code(),
                toDate("2009-03-15")));
        abc123.assignToRoute(new Itinerary(asList(
                new Leg(HONGKONG_TO_NEW_YORK.number(),
                        HONGKONG.code(),
                        NEWYORK.code(),
                        toDate("2009-03-02"),
                        toDate("2009-03-05")),
                new Leg(NEW_YORK_TO_DALLAS.number(),
                        NEWYORK.code(),
                        DALLAS.code(),
                        toDate("2009-03-06"),
                        toDate("2009-03-08")),
                new Leg(DALLAS_TO_HELSINKI.number(),
                        DALLAS.code(),
                        HELSINKI.code(),
                        toDate("2009-03-09"),
                        toDate("2009-03-12"))
        )));

        cargoRepository.add(abc123);

        try {
            HandlingEvent event1 = handlingEventFactory.createHandlingEvent(
                    new Date(),
                    toDate("2009-03-01"),
                    trackingId,
                    null,
                    HONGKONG.code(),
                    HandlingEvent.Type.RECEIVE
            );
            handlingEventRepository.add(event1);

            HandlingEvent event2 = handlingEventFactory.createHandlingEvent(
                    new Date(),
                    toDate("2009-03-02"),
                    trackingId,
                    HONGKONG_TO_NEW_YORK.number(),
                    HONGKONG.code(),
                    HandlingEvent.Type.LOAD
            );
            handlingEventRepository.add(event2);

            HandlingEvent event3 = handlingEventFactory.createHandlingEvent(
                    new Date(),
                    toDate("2009-03-05"),
                    trackingId,
                    HONGKONG_TO_NEW_YORK.number(),
                    NEWYORK.code(),
                    HandlingEvent.Type.UNLOAD
            );
            handlingEventRepository.add(event3);
        } catch (CannotCreateHandlingEventException e) {
            throw new RuntimeException(e);
        }

        HandlingHistory handlingHistory = handlingEventRepository.getHandlingHistoryOfCargo(trackingId);
        abc123.deriveDeliveryProgress(handlingHistory);
        cargoRepository.update(abc123);

        // Cargo JKL567

        RouteSpecification routeSpecification1 = new RouteSpecification(HANGZOU.code(),
                STOCKHOLM.code(),
                toDate("2009-03-18"));
        TrackingId trackingId1 = new TrackingId("JKL567");
        Cargo jkl567 = new Cargo(trackingId1, routeSpecification1);
        jkl567.assignToRoute(new Itinerary(asList(
                new Leg(HONGKONG_TO_NEW_YORK.number(),
                        HANGZOU.code(),
                        NEWYORK.code(),
                        toDate("2009-03-03"),
                        toDate("2009-03-05")),
                new Leg(NEW_YORK_TO_DALLAS.number(),
                        NEWYORK.code(),
                        DALLAS.code(),
                        toDate("2009-03-06"),
                        toDate("2009-03-08")),
                new Leg(DALLAS_TO_HELSINKI.number(),
                        DALLAS.code(),
                        STOCKHOLM.code(),
                        toDate("2009-03-09"),
                        toDate("2009-03-11"))
        )));
        cargoRepository.add(jkl567);
        try {
            HandlingEvent event1 = handlingEventFactory.createHandlingEvent(
                    new Date(),
                    toDate("2009-03-01"),
                    trackingId1,
                    null,
                    HANGZOU.code(),
                    HandlingEvent.Type.RECEIVE
            );
            handlingEventRepository.add(event1);

            HandlingEvent event2 = handlingEventFactory.createHandlingEvent(
                    new Date(),
                    toDate("2009-03-03"),
                    trackingId1,
                    HONGKONG_TO_NEW_YORK.number(),
                    HANGZOU.code(),
                    HandlingEvent.Type.LOAD
            );
            handlingEventRepository.add(event2);

            HandlingEvent event3 = handlingEventFactory.createHandlingEvent(
                    new Date(),
                    toDate("2009-03-05"),
                    trackingId1,
                    HONGKONG_TO_NEW_YORK.number(),
                    NEWYORK.code(),
                    HandlingEvent.Type.UNLOAD
            );
            handlingEventRepository.add(event3);

            HandlingEvent event4 = handlingEventFactory.createHandlingEvent(
                    new Date(),
                    toDate("2009-03-06"),
                    trackingId1,
                    HONGKONG_TO_NEW_YORK.number(),
                    NEWYORK.code(),
                    HandlingEvent.Type.LOAD
            );
            handlingEventRepository.add(event4);
        } catch (CannotCreateHandlingEventException e) {
            throw new RuntimeException(e);
        }

        HandlingHistory handlingHistory1 = handlingEventRepository.getHandlingHistoryOfCargo(trackingId1);
        jkl567.deriveDeliveryProgress(handlingHistory1);
        cargoRepository.update(jkl567);
    }
}
