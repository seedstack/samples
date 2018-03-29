/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.ddd.domain.model.handling;

import java.util.Date;
import java.util.UUID;
import org.apache.commons.lang.Validate;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.seedstack.business.domain.BaseAggregateRoot;
import org.seedstack.business.domain.DomainEvent;
import org.seedstack.business.domain.Identity;
import org.seedstack.business.domain.ValueObject;
import org.seedstack.samples.ddd.domain.model.cargo.TrackingId;
import org.seedstack.samples.ddd.domain.model.location.UnLocode;
import org.seedstack.samples.ddd.domain.model.voyage.Voyage;
import org.seedstack.samples.ddd.domain.model.voyage.VoyageNumber;

/**
 * A HandlingEvent is used to register the event when, for instance,
 * a cargo is unloaded from a carrier at a some loacation at a given time.
 * <p/>
 * The HandlingEvent's are sent from different Incident Logging Applications
 * some time after the event occured and contain information about the
 * {@link org.seedstack.samples.ddd.domain.model.cargo.TrackingId},
 * {@link org.seedstack.samples.ddd.domain.model.location.Location}, timestamp of the completion of the event,
 * and possibly, if applicable a {@link org.seedstack.samples.ddd.domain.model.voyage.Voyage}.
 * <p/>
 * This class is the only member, and consequently the root, of the HandlingEvent aggregate.
 * <p/>
 * HandlingEvent's could contain information about a {@link Voyage} and if so,
 * the event type must be either {@link Type#LOAD} or {@link Type#UNLOAD}.
 * <p/>
 * All other events must be of {@link Type#RECEIVE}, {@link Type#CLAIM} or {@link Type#CUSTOMS}.
 */
@Entity
public final class HandlingEvent extends BaseAggregateRoot<UUID> implements DomainEvent {
    @Id
    @Identity
    private String id = UUID.randomUUID().toString();
    private Type type;
    private VoyageNumber voyage;
    private UnLocode location;
    private Date completionTime;
    private Date registrationTime;
    private TrackingId trackingId;

    private HandlingEvent() {
        // required by persistence
    }

    /**
     * @param trackingId       cargo
     * @param completionTime   completion time, the reported time that the event actually happened (e.g. the receive
     *                         took place).
     * @param registrationTime registration time, the time the message is received
     * @param type             type of event
     * @param location         where the event took place
     * @param voyage           the voyage
     */
    public HandlingEvent(final TrackingId trackingId,
            final Date completionTime,
            final Date registrationTime,
            final Type type,
            final UnLocode location,
            final VoyageNumber voyage) {
        Validate.notNull(trackingId, "Cargo is required");
        Validate.notNull(completionTime, "Completion time is required");
        Validate.notNull(registrationTime, "Registration time is required");
        Validate.notNull(type, "Handling event type is required");
        Validate.notNull(location, "Location is required");
        Validate.notNull(voyage, "Voyage is required");

        if (type.prohibitsVoyage()) {
            throw new IllegalArgumentException("Voyage is not allowed with event type " + type);
        }

        this.voyage = voyage;
        this.completionTime = (Date) completionTime.clone();
        this.registrationTime = (Date) registrationTime.clone();
        this.type = type;
        this.location = location;
        this.trackingId = trackingId;
    }

    /**
     * @param trackingId       cargo
     * @param completionTime   completion time, the reported time that the event actually happened (e.g. the receive
     *                         took place).
     * @param registrationTime registration time, the time the message is received
     * @param type             type of event
     * @param location         where the event took place
     */
    public HandlingEvent(final TrackingId trackingId,
            final Date completionTime,
            final Date registrationTime,
            final Type type,
            final UnLocode location) {
        Validate.notNull(trackingId, "Cargo is required");
        Validate.notNull(completionTime, "Completion time is required");
        Validate.notNull(registrationTime, "Registration time is required");
        Validate.notNull(type, "Handling event type is required");
        Validate.notNull(location, "Location is required");

        if (type.requiresVoyage()) {
            throw new IllegalArgumentException("Voyage is required for event type " + type);
        }

        this.completionTime = (Date) completionTime.clone();
        this.registrationTime = (Date) registrationTime.clone();
        this.type = type;
        this.location = location;
        this.trackingId = trackingId;
        this.voyage = null;
    }

    public Type type() {
        return this.type;
    }

    public VoyageNumber voyage() {
        return this.voyage == null ? new VoyageNumber("") : this.voyage;
    }

    public Date completionTime() {
        return new Date(this.completionTime.getTime());
    }

    public Date registrationTime() {
        return new Date(this.registrationTime.getTime());
    }

    public UnLocode location() {
        return this.location;
    }

    public TrackingId cargo() {
        return this.trackingId;
    }

    /**
     * Handling event type. Either requires or prohibits a carrier movement
     * association, it's never optional.
     */
    public enum Type implements ValueObject {
        LOAD(true),
        UNLOAD(true),
        RECEIVE(false),
        CLAIM(false),
        CUSTOMS(false);

        private final boolean voyageRequired;

        /**
         * Private enum constructor.
         *
         * @param voyageRequired whether or not a voyage is associated with this event type
         */
        private Type(final boolean voyageRequired) {
            this.voyageRequired = voyageRequired;
        }

        /**
         * @return True if a voyage association is required for this event type.
         */
        public boolean requiresVoyage() {
            return voyageRequired;
        }

        /**
         * @return True if a voyage association is prohibited for this event type.
         */
        public boolean prohibitsVoyage() {
            return !requiresVoyage();
        }

    }
}

