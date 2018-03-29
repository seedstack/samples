/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.ddd.infrastructure.pathfinder.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import javax.inject.Inject;
import org.seedstack.samples.ddd.infrastructure.pathfinder.GraphTraversalService;
import org.seedstack.samples.ddd.infrastructure.pathfinder.TransitEdge;
import org.seedstack.samples.ddd.infrastructure.pathfinder.TransitPath;
import org.seedstack.seed.Bind;

@Bind(from = GraphTraversalService.class)
public class GraphTraversalServiceImpl implements GraphTraversalService {
    private static final long ONE_MIN_MS = 1000 * 60;
    private static final long ONE_DAY_MS = ONE_MIN_MS * 60 * 24;
    private GraphDAO dao;
    private Random random;

    @Inject
    public GraphTraversalServiceImpl(GraphDAO dao) {
        this.dao = dao;
        this.random = new Random();
    }

    public List<TransitPath> findShortestPath(final String originNode,
            final String destinationNode,
            final Properties limitations) {
        Date date = nextDate(new Date());

        List<String> allVertices = dao.listAllNodes();
        allVertices.remove(originNode);
        allVertices.remove(destinationNode);

        final int candidateCount = getRandomNumberOfCandidates();
        final List<TransitPath> candidates = new ArrayList<>(candidateCount);

        for (int i = 0; i < candidateCount; i++) {
            allVertices = getRandomChunkOfNodes(allVertices);
            final List<TransitEdge> transitEdges = new ArrayList<>(allVertices.size() - 1);
            final String firstLegTo = allVertices.get(0);

            Date fromDate = nextDate(date);
            Date toDate = nextDate(fromDate);
            date = nextDate(toDate);

            transitEdges.add(new TransitEdge(
                    dao.getTransitEdge(originNode, firstLegTo),
                    originNode, firstLegTo, fromDate, toDate));

            for (int j = 0; j < allVertices.size() - 1; j++) {
                final String curr = allVertices.get(j);
                final String next = allVertices.get(j + 1);
                fromDate = nextDate(date);
                toDate = nextDate(fromDate);
                date = nextDate(toDate);
                transitEdges.add(new TransitEdge(dao.getTransitEdge(curr, next), curr, next, fromDate, toDate));
            }

            final String lastLegFrom = allVertices.get(allVertices.size() - 1);
            fromDate = nextDate(date);
            toDate = nextDate(fromDate);
            transitEdges.add(new TransitEdge(
                    dao.getTransitEdge(lastLegFrom, destinationNode),
                    lastLegFrom, destinationNode, fromDate, toDate));

            candidates.add(new TransitPath(transitEdges));
        }

        return candidates;
    }

    private Date nextDate(Date date) {
        return new Date(date.getTime() + ONE_DAY_MS + (random.nextInt(1000) - 500) * ONE_MIN_MS);
    }

    private int getRandomNumberOfCandidates() {
        return 3 + random.nextInt(3);
    }

    private List<String> getRandomChunkOfNodes(List<String> allNodes) {
        Collections.shuffle(allNodes);
        final int total = allNodes.size();
        final int chunk = total > 4 ? 1 + new Random().nextInt(5) : total;
        return allNodes.subList(0, chunk);
    }

}
