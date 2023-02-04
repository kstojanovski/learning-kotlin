package org.acme.astar

import org.acme.astar.rule.DiagonalScorerKotlin
import org.junit.Test
import kotlin.test.assertEquals

class RouteFinderKotlinTest {

    @Test
    fun findPathToWeightTrue() {
        //Arrange
        val from = AStarKotlin.Node(0, 0, 0)
        val to = AStarKotlin.Node(7, 7, 0)
        val routeFinder = RouteFinderKotlin(maze, DiagonalScorerKotlin(true), from, to)
        val expectedPathTo = ArrayList<AStarKotlin.Node>()

        for (i in 0..7) {
            expectedPathTo.add(AStarKotlin.Node(i, i, maze[i][i]))
        }

        //Act
        val actualPathTo = routeFinder.findPathTo()

        //Assert
        assertEquals(expectedPathTo, actualPathTo)
    }

    @Test
    fun findPathToWeightFalse() {
        //Arrange
        val from = AStarKotlin.Node(0, 0, 0)
        val to = AStarKotlin.Node(7, 7, 0)
        val routeFinder =
            RouteFinderKotlin(maze, DiagonalScorerKotlin(false), from, to)
        val expectedPathTo = ArrayList<AStarKotlin.Node>()

        var x = 0
        var y = 0; expectedPathTo.add(AStarKotlin.Node(x, y, maze[y][x]))
        x = 1; y = 1; expectedPathTo.add(AStarKotlin.Node(x, y, maze[y][x]))
        x = 2; expectedPathTo.add(AStarKotlin.Node(x, y, maze[y][x]))
        x = 3; expectedPathTo.add(AStarKotlin.Node(x, y, maze[y][x]))
        x = 4; expectedPathTo.add(AStarKotlin.Node(x, y, maze[y][x]))
        x = 5; expectedPathTo.add(AStarKotlin.Node(x, y, maze[y][x]))
        x = 6; y = 2; expectedPathTo.add(AStarKotlin.Node(x, y, maze[y][x]))
        y = 3; expectedPathTo.add(AStarKotlin.Node(x, y, maze[y][x]))
        y = 4; expectedPathTo.add(AStarKotlin.Node(x, y, maze[y][x]))
        y = 5; expectedPathTo.add(AStarKotlin.Node(x, y, maze[y][x]))
        y = 6; expectedPathTo.add(AStarKotlin.Node(x, y, maze[y][x]))
        x = 7; y = 7; expectedPathTo.add(AStarKotlin.Node(x, y, maze[y][x]))

        //Act
        val actualPathTo = routeFinder.findPathTo()

        //Assert
        assertEquals(expectedPathTo, actualPathTo)
    }
}