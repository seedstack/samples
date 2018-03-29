/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.ddd.domain.model.voyage;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.Validate;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.seedstack.business.domain.BaseAggregateRoot;
import org.seedstack.business.domain.Identity;
import org.seedstack.samples.ddd.domain.model.location.UnLocode;

/**
 * A Voyage.
 */
@Entity
public class Voyage extends BaseAggregateRoot<VoyageNumber> {
    public static final Voyage NONE = new Voyage(
            new VoyageNumber(""), Schedule.EMPTY
    );
    @Id
    @Identity
    private VoyageNumber voyageNumber;
    private Schedule schedule;

    private Voyage() {
        // required by persistence
    }

    public Voyage(final VoyageNumber voyageNumber, final Schedule schedule) {
        this.voyageNumber = checkNotNull(voyageNumber, "Voyage number is required");
        this.schedule = checkNotNull(schedule, "Schedule is required");
    }

    /**
     * @return Voyage number.
     */
    public VoyageNumber number() {
        return voyageNumber;
    }

    /**
     * @return Schedule.
     */
    public Schedule schedule() {
        return schedule;
    }

    /**
     * Builder pattern is used for incremental construction
     * of a Voyage aggregate. This serves as an aggregate factory.
     */
    public static final class Builder {
        private final List<CarrierMovement> carrierMovements = new ArrayList<>();
        private final VoyageNumber voyageNumber;
        private UnLocode departureLocation;

        public Builder(final VoyageNumber voyageNumber, final UnLocode departureLocation) {
            Validate.notNull(voyageNumber, "Voyage number is required");
            Validate.notNull(departureLocation, "Departure location is required");

            this.voyageNumber = voyageNumber;
            this.departureLocation = departureLocation;
        }

        public Builder addMovement(UnLocode arrivalLocation, Date departureTime, Date arrivalTime) {
            carrierMovements.add(new CarrierMovement(departureLocation, arrivalLocation, departureTime, arrivalTime));
            // Next departure location is the same as this arrival location
            this.departureLocation = arrivalLocation;
            return this;
        }

        public Voyage build() {
            return new Voyage(voyageNumber, new Schedule(carrierMovements));
        }
    }
}
