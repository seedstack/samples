/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.ddd.application.samples;

import static org.seedstack.samples.ddd.application.samples.SampleLocations.CHICAGO;
import static org.seedstack.samples.ddd.application.samples.SampleLocations.DALLAS;
import static org.seedstack.samples.ddd.application.samples.SampleLocations.HAMBURG;
import static org.seedstack.samples.ddd.application.samples.SampleLocations.HANGZOU;
import static org.seedstack.samples.ddd.application.samples.SampleLocations.HELSINKI;
import static org.seedstack.samples.ddd.application.samples.SampleLocations.HONGKONG;
import static org.seedstack.samples.ddd.application.samples.SampleLocations.MELBOURNE;
import static org.seedstack.samples.ddd.application.samples.SampleLocations.NEWYORK;
import static org.seedstack.samples.ddd.application.samples.SampleLocations.ROTTERDAM;
import static org.seedstack.samples.ddd.application.samples.SampleLocations.SHANGHAI;
import static org.seedstack.samples.ddd.application.samples.SampleLocations.STOCKHOLM;
import static org.seedstack.samples.ddd.application.samples.SampleLocations.TOKYO;
import static org.seedstack.samples.ddd.application.util.DateTestUtil.toDate;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.seedstack.samples.ddd.domain.model.location.Location;
import org.seedstack.samples.ddd.domain.model.voyage.CarrierMovement;
import org.seedstack.samples.ddd.domain.model.voyage.Schedule;
import org.seedstack.samples.ddd.domain.model.voyage.Voyage;
import org.seedstack.samples.ddd.domain.model.voyage.VoyageNumber;

/**
 * Sample carrier movements, for test purposes.
 */
public class SampleVoyages {
    public static final Voyage CM001 = createVoyage("CM001", STOCKHOLM, HAMBURG);
    public static final Voyage CM002 = createVoyage("CM002", HAMBURG, HONGKONG);
    public static final Voyage CM003 = createVoyage("CM003", HONGKONG, NEWYORK);
    public static final Voyage CM004 = createVoyage("CM004", NEWYORK, CHICAGO);
    public static final Voyage CM005 = createVoyage("CM005", CHICAGO, HAMBURG);
    public static final Voyage CM006 = createVoyage("CM006", HAMBURG, HANGZOU);
    public final static Voyage v100 = new Voyage.Builder(new VoyageNumber("V100"), HONGKONG.code()).
            addMovement(TOKYO.code(), toDate("2009-03-03"), toDate("2009-03-05")).
            addMovement(NEWYORK.code(), toDate("2009-03-06"), toDate("2009-03-09")).
            build();

    // TODO CM00[1-6] and createVoyage are deprecated. Remove and refactor tests.
    public final static Voyage v200 = new Voyage.Builder(new VoyageNumber("V200"), TOKYO.code()).
            addMovement(NEWYORK.code(), toDate("2009-03-06"), toDate("2009-03-08")).
            addMovement(CHICAGO.code(), toDate("2009-03-10"), toDate("2009-03-14")).
            addMovement(STOCKHOLM.code(), toDate("2009-03-14"), toDate("2009-03-16")).
            build();
    public final static Voyage v300 = new Voyage.Builder(new VoyageNumber("V300"), TOKYO.code()).
            addMovement(ROTTERDAM.code(), toDate("2009-03-08"), toDate("2009-03-11")).
            addMovement(HAMBURG.code(), toDate("2009-03-11"), toDate("2009-03-12")).
            addMovement(MELBOURNE.code(), toDate("2009-03-14"), toDate("2009-03-18")).
            addMovement(TOKYO.code(), toDate("2009-03-19"), toDate("2009-03-21")).
            build();
    public final static Voyage v400 = new Voyage.Builder(new VoyageNumber("V400"), HAMBURG.code()).
            addMovement(STOCKHOLM.code(), toDate("2009-03-14"), toDate("2009-03-15")).
            addMovement(HELSINKI.code(), toDate("2009-03-15"), toDate("2009-03-16")).
            addMovement(HAMBURG.code(), toDate("2009-03-20"), toDate("2009-03-22")).
            build();
    /**
     * Voyage number 0100S (by ship)
     * <p>
     * Hongkong - Hangzou - Tokyo - Melbourne - New York
     */
    public static final Voyage HONGKONG_TO_NEW_YORK =
            new Voyage.Builder(new VoyageNumber("0100S"), HONGKONG.code()).
                    addMovement(HANGZOU.code(), toDate("2008-10-01", "12:00"), toDate("2008-10-03", "14:30")).
                    addMovement(TOKYO.code(), toDate("2008-10-03", "21:00"), toDate("2008-10-06", "06:15")).
                    addMovement(MELBOURNE.code(), toDate("2008-10-06", "11:00"), toDate("2008-10-12", "11:30")).
                    addMovement(NEWYORK.code(), toDate("2008-10-14", "12:00"), toDate("2008-10-23", "23:10")).
                    build();
    /**
     * Voyage number 0200T (by train)
     * <p>
     * New York - Chicago - Dallas
     */
    public static final Voyage NEW_YORK_TO_DALLAS =
            new Voyage.Builder(new VoyageNumber("0200T"), NEWYORK.code()).
                    addMovement(CHICAGO.code(), toDate("2008-10-24", "07:00"), toDate("2008-10-24", "17:45")).
                    addMovement(DALLAS.code(), toDate("2008-10-24", "21:25"), toDate("2008-10-25", "19:30")).
                    build();
    /**
     * Voyage number 0300A (by airplane)
     * <p>
     * Dallas - Hamburg - Stockholm - Helsinki
     */
    public static final Voyage DALLAS_TO_HELSINKI =
            new Voyage.Builder(new VoyageNumber("0300A"), DALLAS.code()).
                    addMovement(HAMBURG.code(), toDate("2008-10-29", "03:30"), toDate("2008-10-31", "14:00")).
                    addMovement(STOCKHOLM.code(), toDate("2008-11-01", "15:20"), toDate("2008-11-01", "18:40")).
                    addMovement(HELSINKI.code(), toDate("2008-11-02", "09:00"), toDate("2008-11-02", "11:15")).
                    build();
    /**
     * Voyage number 0301S (by ship)
     * <p>
     * Dallas - Hamburg - Stockholm - Helsinki, alternate route
     */
    public static final Voyage DALLAS_TO_HELSINKI_ALT =
            new Voyage.Builder(new VoyageNumber("0301S"), DALLAS.code()).
                    addMovement(HELSINKI.code(), toDate("2008-10-29", "03:30"), toDate("2008-11-05", "15:45")).
                    build();
    /**
     * Voyage number 0400S (by ship)
     * <p>
     * Helsinki - Rotterdam - Shanghai - Hongkong
     */
    public static final Voyage HELSINKI_TO_HONGKONG =
            new Voyage.Builder(new VoyageNumber("0400S"), HELSINKI.code()).
                    addMovement(ROTTERDAM.code(), toDate("2008-11-04", "05:50"), toDate("2008-11-06", "14:10")).
                    addMovement(SHANGHAI.code(), toDate("2008-11-10", "21:45"), toDate("2008-11-22", "16:40")).
                    addMovement(HONGKONG.code(), toDate("2008-11-24", "07:00"), toDate("2008-11-28", "13:37")).
                    build();
    public static final Map<VoyageNumber, Voyage> ALL = new HashMap<>();

    static {
        for (Field field : SampleVoyages.class.getDeclaredFields()) {
            if (field.getType().equals(Voyage.class)) {
                try {
                    Voyage voyage = (Voyage) field.get(null);
                    ALL.put(voyage.number(), voyage);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private static Voyage createVoyage(String id, Location from, Location to) {
        return new Voyage(new VoyageNumber(id), new Schedule(Collections.singletonList(
                new CarrierMovement(from.code(), to.code(), new Date(), new Date())
        )));
    }

    public static List<Voyage> getAll() {
        return new ArrayList<>(ALL.values());
    }

    public static Voyage lookup(VoyageNumber voyageNumber) {
        return ALL.get(voyageNumber);
    }

}
