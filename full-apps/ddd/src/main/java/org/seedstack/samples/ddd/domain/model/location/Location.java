/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.ddd.domain.model.location;

import static com.google.common.base.Preconditions.checkNotNull;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.seedstack.business.domain.BaseAggregateRoot;
import org.seedstack.business.domain.Identity;

/**
 * A location is our model is stops on a journey, such as cargo
 * origin or destination, or carrier movement endpoints.
 *
 * It is uniquely identified by a UN Locode.
 */
@Entity
public final class Location extends BaseAggregateRoot<UnLocode> {
    /**
     * Special Location object that marks an unknown location.
     */
    public static final Location UNKNOWN = new Location(
            new UnLocode("XXXXX"), "Unknown location"
    );

    @Id
    @Identity
    private UnLocode id;
    private String name;

    private Location() {
        // required by persistence
    }

    /**
     * Constructor.
     *
     * @param id   UN Locode
     * @param name location name
     * @throws IllegalArgumentException if the UN Locode or name is null
     */
    public Location(final UnLocode id, final String name) {
        this.id = checkNotNull(id);
        this.name = checkNotNull(name);
    }

    /**
     * @return UN Locode for this location.
     */
    public UnLocode code() {
        return id;
    }

    /**
     * @return Actual name of this location, e.g. "Stockholm".
     */
    public String name() {
        return name;
    }
}
