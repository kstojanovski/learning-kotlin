package org.acme.astar

import org.acme.astar.rule.DiagonalScorerKotlin

class AStarKotlin(val maze: Array<IntArray>, x: Int, y: Int, private val ignoreWeight: Boolean) {

    private val startField: Node

    init {
        this.startField = Node(x, y, maze[x][y])
    }

    fun findPathTo(x: Int, y: Int): List<Node> {
        val endField = Node(x, y, maze[x][y])
        val routeFinder = RouteFinderKotlin(maze, DiagonalScorerKotlin(ignoreWeight), startField, endField)
        return routeFinder.findPathTo()
    }

    class Node(val x: Int, val y: Int, val w: Int) {
        var g: Double = 0.0

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Node

            if (x != other.x) return false
            if (y != other.y) return false

            return true
        }

        override fun hashCode(): Int {
            var result = x
            result = 31 * result + y
            return result
        }

    }
}
