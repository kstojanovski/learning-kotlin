package org.acme.astar.util;

import org.acme.astar.AStar;
import org.acme.astar.AStarUsage;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class MazeUtilTest {

    @Test
    public void testGetNeighbourFields() {
        //Arrange
        int[][] maze = AStarUsage.maze;
        MazeUtil<AStar.Node> mazeUtil = new MazeUtil<>(maze);
        int x = 3;
        int y = 3;
        Set<AStar.Node> expectedNeighbourFields = new HashSet<>(Set.of(
                new AStar.Node(2, 2, maze[x][y]),
                new AStar.Node(2, 3, maze[x][y]),
                new AStar.Node(2, 4, maze[x][y]),
                new AStar.Node(3, 2, maze[x][y]),
                new AStar.Node(3, 4, maze[x][y]),
                new AStar.Node(4, 2, maze[x][y]),
                new AStar.Node(4, 3, maze[x][y]),
                new AStar.Node(4, 4, maze[x][y])
        ));

        //Act
        Set<AStar.Node> actualNeighbourFields = mazeUtil.getNeighbourFields(new AStar.Node(x, y, maze[x][y]));

        //Act
        assertEquals(expectedNeighbourFields, actualNeighbourFields);
    }

    @Test
    public void testGetNeighbourFieldsStart() {
        //Arrange
        int[][] maze = AStarUsage.maze;
        MazeUtil<AStar.Node> mazeUtil = new MazeUtil<>(maze);
        int x = 0;
        int y = 0;
        Set<AStar.Node> expectedNeighbourFields = new HashSet<>(Set.of(
                new AStar.Node(0, 1, maze[x][y]),
                new AStar.Node(1, 1, maze[x][y]),
                new AStar.Node(1, 0, maze[x][y])
        ));

        //Act
        Set<AStar.Node> actualNeighbourFields = mazeUtil.getNeighbourFields(new AStar.Node(x, y, maze[x][y]));

        //Act
        assertEquals(expectedNeighbourFields, actualNeighbourFields);
    }

    @Test
    public void testGetNeighbourFieldsEnd() {
        //Arrange
        int[][] maze = AStarUsage.maze;
        MazeUtil<AStar.Node> mazeUtil = new MazeUtil<>(maze);
        int x = 7;
        int y = 7;
        Set<AStar.Node> expectedNeighbourFields = new HashSet<>(Set.of(
                new AStar.Node(6, 7, maze[x][y]),
                new AStar.Node(6, 6, maze[x][y]),
                new AStar.Node(7, 6, maze[x][y])
        ));

        //Act
        Set<AStar.Node> actualNeighbourFields = mazeUtil.getNeighbourFields(new AStar.Node(x, y, maze[x][y]));

        //Act
        assertEquals(expectedNeighbourFields, actualNeighbourFields);
    }
}