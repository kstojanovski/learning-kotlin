package org.acme.astar.util;

import org.acme.astar.AStar;

import java.util.HashSet;
import java.util.Set;

public class MazeUtil<T extends AStar.Node> {

    private final int[][] maze;

    public MazeUtil(int[][] maze) {
        this.maze = maze;
    }

    /**
     * Find all neighbour fields of the current one and return in set with them.
     *
     * @param currentField the field on which the neighbour nodes are found.
     * @return all neighbour nodes.
     */
    public Set<T> getNeighbourFields(T currentField) {
        Set<T> neighbourFields = new HashSet<>();
        int fieldPosX = currentField.getX();
        int fieldPosY = currentField.getY();
        int lengthX = fieldPosX + 1;
        int lengthY = fieldPosY + 1;
        for (int x = fieldPosX - 1; x <= lengthX; x++) {
            for (int y = fieldPosY - 1; y <= lengthY; y++) {
                if ((x != fieldPosX || y != fieldPosY) //do not put the current node into the set.
                        && (x > -1) && (x < 8) //do not put node into the set if the X position is out of the table.
                        && (y > -1) && (y < 8)) { //do not put node into the set if the Y position is out of the table.
                    @SuppressWarnings("unchecked") T neighbourField = (T) new AStar.Node(x, y, maze[y][x]);
                    neighbourFields.add(neighbourField);
                }
            }
        }
        return neighbourFields;
    }
}
