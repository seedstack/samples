/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.ddd.domain.model.cargo;

import org.apache.commons.lang.Validate;
import org.mongodb.morphia.annotations.Embedded;
import org.seedstack.business.domain.BaseValueObject;
import org.seedstack.samples.ddd.domain.model.handling.HandlingEvent;
import org.seedstack.samples.ddd.domain.model.location.UnLocode;
import org.seedstack.samples.ddd.domain.model.voyage.VoyageNumber;

/**
 * A handling activity represents how and where a cargo can be handled,
 * and can be used to express predictions about what is expected to
 * happen to a cargo in the future.
 */
@Embedded
public class HandlingActivity extends BaseValueObject {

    // TODO make HandlingActivity a part of HandlingEvent too? There is some overlap.

    private HandlingEvent.Type type;
    private UnLocode location;
    private VoyageNumber voyage;

    private HandlingActivity() {
        // required by persistence
    }

    public HandlingActivity(final HandlingEvent.Type type, final UnLocode location) {
        Validate.notNull(type, "Handling event type is required");
        Validate.notNull(location, "Location is required");

        this.type = type;
        this.location = location;
    }

    public HandlingActivity(final HandlingEvent.Type type, final UnLocode location, final VoyageNumber voyage) {
        Validate.notNull(type, "Handling event type is required");
        Validate.notNull(location, "Location is required");
        Validate.notNull(location, "Voyage is required");

        this.type = type;
        this.location = location;
        this.voyage = voyage;
    }

    public HandlingEvent.Type type() {
        return type;
    }

    public UnLocode location() {
        return location;
    }

    public VoyageNumber voyage() {
        return voyage;
    }
}
