package org.acme.astar.rule;

import org.acme.astar.AStar;

/**
 * Idea seen at <a href="https://www.geeksforgeeks.org/a-search-algorithm/">a-search-algorithm</a>.<br>
 * The weight of the fields is also considered for the heuristics.
 * <br><br>
 * It is nothing but the maximum of absolute values of differences in the goal’s x and y coordinates and the current
 * cell’s x and y coordinates respectively.<br>
 * When to use this heuristic? – When we are allowed to move in eight directions only (similar to a move of a King in
 * Chess).<br>
 * The Diagonal Distance Heuristics is shown by the below figure (assume red spot as source cell and green spot as
 * target cell).
 */
public class DiagonalScorer implements Scorer<AStar.Node> {

    private final boolean ignoreWeight;

    public DiagonalScorer(boolean ignoreWeight) {
        this.ignoreWeight = ignoreWeight;
    }

    @Override
    public double computeCost(AStar.Node from, AStar.Node to) {
        double dx = Math.abs(from.getX() - to.getX());
        double dy = Math.abs(from.getY() - to.getY());
        double diagonalScorer = (dx + dy) + (Math.sqrt(2) - 2) * Math.min(dx, dy);
        return ignoreWeight ? diagonalScorer : diagonalScorer + to.getW();
    }
}
