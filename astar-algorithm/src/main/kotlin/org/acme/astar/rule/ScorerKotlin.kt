package org.acme.astar.rule

import org.acme.astar.AStarKotlin

interface ScorerKotlin<T : AStarKotlin.Node?> {
    fun computeCost(from: T, to: T): Double
}