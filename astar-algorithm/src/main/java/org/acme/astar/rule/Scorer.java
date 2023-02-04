package org.acme.astar.rule;

import org.acme.astar.AStar;

public interface Scorer<T extends AStar.Node> {
    double computeCost(T from, T to);
}