package org.acme.astar.rule

import org.acme.astar.AStarKotlin
import kotlin.math.abs
import kotlin.math.sqrt

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
class DiagonalScorerKotlin(private val ignoreWeight: Boolean) : ScorerKotlin<AStarKotlin.Node> {

    override fun computeCost(from: AStarKotlin.Node, to: AStarKotlin.Node): Double {
        val dx = abs(from.x - to.x)
        val dy = abs(from.y - to.y)
        val diagonalScorer = (dx + dy) + (sqrt(2.0) - 2) * dx.coerceAtMost(dy)
        return if (this.ignoreWeight) {
            diagonalScorer
        } else {
            diagonalScorer + to.w
        }
    }
}
