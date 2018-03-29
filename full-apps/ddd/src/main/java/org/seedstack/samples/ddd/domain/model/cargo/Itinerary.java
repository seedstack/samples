/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.ddd.domain.model.cargo;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.Validate;
import org.mongodb.morphia.annotations.Embedded;
import org.seedstack.business.domain.BaseValueObject;
import org.seedstack.samples.ddd.domain.model.handling.HandlingEvent;
import org.seedstack.samples.ddd.domain.model.location.Location;
import org.seedstack.samples.ddd.domain.model.location.UnLocode;

/**
 * An itinerary.
 */
@Embedded
public class Itinerary extends BaseValueObject {
    static final Itinerary EMPTY_ITINERARY = new Itinerary();
    private static final Date END_OF_DAYS = new Date(Long.MAX_VALUE);
    private List<Leg> legs = Collections.emptyList();

    private Itinerary() {
        // required by persistence
    }

    /**
     * Constructor.
     *
     * @param legs List of legs for this itinerary.
     */
    public Itinerary(final List<Leg> legs) {
        Validate.notEmpty(legs);
        Validate.noNullElements(legs);

        this.legs = legs;
    }

    /**
     * @return the legs of this itinerary, as an <b>immutable</b> list.
     */
    public List<Leg> legs() {
        return Collections.unmodifiableList(legs);
    }

    /**
     * Test if the given handling event is expected when executing this itinerary.
     *
     * @param event Event to test.
     * @return <code>true</code> if the event is expected
     */
    public boolean isExpected(final HandlingEvent event) {
        if (legs.isEmpty()) {
            return true;
        }

        if (event.type() == HandlingEvent.Type.RECEIVE) {
            //Check that the first leg's origin is the event's location
            final Leg leg = legs.get(0);
            return (leg.loadLocation().equals(event.location()));
        }

        if (event.type() == HandlingEvent.Type.LOAD) {
            //Check that the there is one leg with same load location and voyage
            for (Leg leg : legs) {
                if (leg.loadLocation().equals(event.location()) &&
                        leg.voyage().equals(event.voyage()))
                    return true;
            }
            return false;
        }

        if (event.type() == HandlingEvent.Type.UNLOAD) {
            //Check that the there is one leg with same unload location and voyage
            for (Leg leg : legs) {
                if (leg.unloadLocation().equals(event.location()) &&
                        leg.voyage().equals(event.voyage()))
                    return true;
            }
            return false;
        }

        if (event.type() == HandlingEvent.Type.CLAIM) {
            //Check that the last leg's destination is from the event's location
            final Leg leg = lastLeg();
            return (leg.unloadLocation().equals(event.location()));
        }

        //HandlingEvent.Type.CUSTOMS;
        return true;
    }

    /**
     * @return The initial departure location.
     */
    UnLocode initialDepartureLocation() {
        if (legs.isEmpty()) {
            return Location.UNKNOWN.code();
        } else {
            return legs.get(0).loadLocation();
        }
    }

    /**
     * @return The final arrival location.
     */
    UnLocode finalArrivalLocation() {
        if (legs.isEmpty()) {
            return Location.UNKNOWN.code();
        } else {
            return lastLeg().unloadLocation();
        }
    }

    /**
     * @return Date when cargo arrives at final destination.
     */
    Date finalArrivalDate() {
        final Leg lastLeg = lastLeg();

        if (lastLeg == null) {
            return new Date(END_OF_DAYS.getTime());
        } else {
            return new Date(lastLeg.unloadTime().getTime());
        }
    }

    /**
     * @return The last leg on the itinerary.
     */
    Leg lastLeg() {
        if (legs.isEmpty()) {
            return null;
        } else {
            return legs.get(legs.size() - 1);
        }
    }
}
