/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.ddd.infrastructure.pathfinder;

import java.rmi.Remote;
import java.util.List;
import java.util.Properties;

/**
 * Part of the external graph traversal API exposed by the routing team
 * and used by us (booking and tracking team).
 */
public interface GraphTraversalService extends Remote {

    /**
     * @param origin      origin point
     * @param destination destination point
     * @param limitations restrictions on the path selection, as key-value according to some API specification
     * @return A list of transit paths
     */
    List<TransitPath> findShortestPath(String origin,
            String destination,
            Properties limitations);

}
