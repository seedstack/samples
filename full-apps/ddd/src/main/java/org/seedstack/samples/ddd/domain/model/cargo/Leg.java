/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.ddd.domain.model.cargo;

import java.util.Date;

import dev.morphia.annotations.Embedded;
import org.apache.commons.lang3.Validate;
import org.seedstack.business.domain.BaseValueObject;
import org.seedstack.samples.ddd.domain.model.location.UnLocode;
import org.seedstack.samples.ddd.domain.model.voyage.VoyageNumber;

/**
 * An itinerary consists of one or more legs.
 */
@Embedded
public class Leg extends BaseValueObject {
    private VoyageNumber voyage;
    private UnLocode loadLocation;
    private UnLocode unloadLocation;
    private Date loadTime;
    private Date unloadTime;

    private Leg() {
        // required by persistence
    }

    public Leg(VoyageNumber voyage, UnLocode loadLocation, UnLocode unloadLocation, Date loadTime, Date unloadTime) {
        Validate.noNullElements(new Object[]{voyage, loadLocation, unloadLocation, loadTime, unloadTime});

        this.voyage = voyage;
        this.loadLocation = loadLocation;
        this.unloadLocation = unloadLocation;
        this.loadTime = loadTime;
        this.unloadTime = unloadTime;
    }

    public VoyageNumber voyage() {
        return voyage;
    }

    public UnLocode loadLocation() {
        return loadLocation;
    }

    public UnLocode unloadLocation() {
        return unloadLocation;
    }

    public Date loadTime() {
        return new Date(loadTime.getTime());
    }

    public Date unloadTime() {
        return new Date(unloadTime.getTime());
    }
}
