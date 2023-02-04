package org.acme.astar;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.acme.astar.rule.DiagonalScorer;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)
public class RouteFinderTest {

    @Test
    @Parameters({"true", "false"})
    public void findPathTo(boolean ignoreWeight) {
        //Arrange
        RouteFinder<AStar.Node> routeFinder = new RouteFinder<>(AStarUsage.maze, new DiagonalScorer(ignoreWeight));
        AStar.Node from = new AStar.Node(0, 0, 0);
        AStar.Node to = new AStar.Node(7, 7, 0);
        List<AStar.Node> expectedPathTo = new ArrayList<>();

        if (ignoreWeight) {
            for (int i = 0; i < 8; i++) {
                expectedPathTo.add(new AStar.Node(i,i, AStarUsage.maze[i][i]));
            }
        } else {
            int x = 0, y = 0; expectedPathTo.add(new AStar.Node(x,y, AStarUsage.maze[y][x]));
            x = 1; y = 1; expectedPathTo.add(new AStar.Node(x,y, AStarUsage.maze[y][x]));
            x = 2; expectedPathTo.add(new AStar.Node(x,y, AStarUsage.maze[y][x]));
            x = 3; expectedPathTo.add(new AStar.Node(x,y, AStarUsage.maze[y][x]));
            x = 4; expectedPathTo.add(new AStar.Node(x,y, AStarUsage.maze[y][x]));
            x = 5; expectedPathTo.add(new AStar.Node(x,y, AStarUsage.maze[y][x]));
            x = 6; y = 2; expectedPathTo.add(new AStar.Node(x,y, AStarUsage.maze[y][x]));
            y = 3; expectedPathTo.add(new AStar.Node(x,y, AStarUsage.maze[y][x]));
            y = 4; expectedPathTo.add(new AStar.Node(x,y, AStarUsage.maze[y][x]));
            y = 5; expectedPathTo.add(new AStar.Node(x,y, AStarUsage.maze[y][x]));
            x = 7; y = 6; expectedPathTo.add(new AStar.Node(x,y, AStarUsage.maze[y][x]));
            y = 7; expectedPathTo.add(new AStar.Node(x,y, AStarUsage.maze[y][x]));
        }

        //Act
        List<AStar.Node> actualPathTo = routeFinder.findPathTo(from, to);

        //Assert
        assertEquals(expectedPathTo, actualPathTo);
    }
}