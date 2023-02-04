package org.acme.astar

import java.util.StringJoiner

class RouteNodeKotlin<T : AStarKotlin.Node?>(current: T, previous: T?, routeScore: Double, estimatedScore: Double) : Comparable<RouteNodeKotlin<T>> {
    val current: T
    var previous: T?
    /**
     * already traveled route distance (incl. the distance to this one).
     */
    var routeScore: Double
    var estimatedScore: Double

    init {
        this.current = current
        this.previous = previous
        this.routeScore = routeScore
        this.estimatedScore = estimatedScore
    }

    override fun compareTo(other: RouteNodeKotlin<T>): Int {
        return this.estimatedScore.compareTo(other.estimatedScore)
    }

    override fun toString(): String =
        StringJoiner(", ", Class.forName("org.acme.astar.RouteNodeKotlin").kotlin.simpleName + "[", "]")
            .add("current=$current").add("previous=$previous").add("routeScore=$routeScore").add("estimatedScore=$estimatedScore")
                .toString()
}