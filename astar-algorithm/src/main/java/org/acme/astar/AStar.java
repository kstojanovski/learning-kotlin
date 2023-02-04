package org.acme.astar;

import org.acme.astar.rule.DiagonalScorer;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Objects;

@Slf4j
public class AStar {

    private final int[][] maze;
    private final Node startField;

    private final RouteFinder<Node> routeFinder;

    public AStar(int[][] maze, int x, int y, boolean ignoreWeight) {
        this.maze = maze;
        this.startField = new Node(x, y, maze[x][y]);
        routeFinder = new RouteFinder<>(maze, new DiagonalScorer(ignoreWeight));
    }

    public List<Node> findPathTo(int x, int y) {
        Node endField = new Node(x, y, maze[x][y]);
        return routeFinder.findPathTo(startField, endField);
    }

    public static class Node {

        private final int x;
        private final int y;
        private final double w;
        private double g;

        public Node(int x, int y, double w) {
            this.x = x;
            this.y = y;
            this.w = w;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public double getW() {
            return w;
        }

        public double getG() {
            return g;
        }

        public void setG(double g) {
            this.g = g;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return x == node.x && y == node.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

    }
}
