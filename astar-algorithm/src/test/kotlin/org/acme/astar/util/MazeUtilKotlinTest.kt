package org.acme.astar.util

import org.acme.astar.AStarKotlin
import org.acme.astar.maze
import org.junit.Test
import kotlin.test.assertEquals

class MazeUtilKotlinTest {

    @Test
    fun testGetNeighbourFields() {
        //Arrange
        val mazeUtilKotlin = MazeUtilKotlin<AStarKotlin.Node>(maze)
        val x = 3
        val y = 3
        val expectedNeighbourFields = HashSet<AStarKotlin.Node>(
            mutableSetOf(
                AStarKotlin.Node(2, 2, maze[x][y]),
                AStarKotlin.Node(2, 3, maze[x][y]),
                AStarKotlin.Node(2, 4, maze[x][y]),
                AStarKotlin.Node(3, 2, maze[x][y]),
                AStarKotlin.Node(3, 4, maze[x][y]),
                AStarKotlin.Node(4, 2, maze[x][y]),
                AStarKotlin.Node(4, 3, maze[x][y]),
                AStarKotlin.Node(4, 4, maze[x][y])
            )
        )

        //Act
        val actualNeighbourFields = mazeUtilKotlin.getNeighbourFields(AStarKotlin.Node(x, y, maze[x][y]))

        //Act
        assertEquals(expectedNeighbourFields, actualNeighbourFields)
    }

    @Test
    fun testGetNeighbourFieldsStart() {
        //Arrange
        val mazeUtil = MazeUtilKotlin<AStarKotlin.Node>(maze)
        val x = 0
        val y = 0
        val expectedNeighbourFields = HashSet<AStarKotlin.Node>(
            mutableSetOf(
                AStarKotlin.Node(0, 1, maze[x][y]),
                AStarKotlin.Node(1, 1, maze[x][y]),
                AStarKotlin.Node(1, 0, maze[x][y])
            )
        )

        //Act
        val actualNeighbourFields = mazeUtil.getNeighbourFields(AStarKotlin.Node(x, y, maze[x][y]))

        //Act
        assertEquals(expectedNeighbourFields, actualNeighbourFields)
    }

    @Test
    fun testGetNeighbourFieldsEnd() {
        //Arrange
        val mazeUtil = MazeUtilKotlin<AStarKotlin.Node>(maze)
        val x = 7
        val y = 7
        val expectedNeighbourFields = HashSet<AStarKotlin.Node>(
            mutableSetOf(
                AStarKotlin.Node(6, 7, maze[x][y]),
                AStarKotlin.Node(6, 6, maze[x][y]),
                AStarKotlin.Node(7, 6, maze[x][y])
            )
        )

        //Act
        val actualNeighbourFields = mazeUtil.getNeighbourFields(AStarKotlin.Node(x, y, maze[x][y]))

        //Act
        assertEquals(expectedNeighbourFields, actualNeighbourFields)
    }
}