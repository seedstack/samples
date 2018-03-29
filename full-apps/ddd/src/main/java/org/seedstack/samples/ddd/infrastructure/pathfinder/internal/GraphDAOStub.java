/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.ddd.infrastructure.pathfinder.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.seedstack.seed.Bind;

@Bind(from = GraphDAO.class)
public class GraphDAOStub implements GraphDAO {
    private static final Random random = new Random();

    public List<String> listAllNodes() {
        return new ArrayList<>(Arrays.asList(
                "CNHKG",
                "AUMEL",
                "SESTO",
                "FIHEL",
                "USCHI",
                "JNTKO",
                "DEHAM",
                "CNSHA",
                "NLRTM",
                "SEGOT",
                "CNHGH",
                "USNYC",
                "USDAL"
        ));
    }

    public String getTransitEdge(String from, String to) {
        final int i = random.nextInt(5);
        if (i == 0) return "0100S";
        if (i == 1) return "0200T";
        if (i == 2) return "0300A";
        if (i == 3) return "0301S";
        return "0400S";
    }
}
