/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.ddd.infrastructure.pathfinder;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 *
 */
public final class TransitPath implements Serializable {

    private final List<TransitEdge> transitEdges;

    /**
     * Constructor.
     *
     * @param transitEdges The legs for this itinerary.
     */
    public TransitPath(final List<TransitEdge> transitEdges) {
        this.transitEdges = transitEdges;
    }

    /**
     * @return An unmodifiable list DTOs.
     */
    public List<TransitEdge> getTransitEdges() {
        return Collections.unmodifiableList(transitEdges);
    }
}
