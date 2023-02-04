package org.acme.astar

import org.acme.astar.rule.ScorerKotlin
import org.acme.astar.util.MazeUtilKotlin
import java.util.*
import java.util.stream.Collectors

class RouteFinderKotlin<T : AStarKotlin.Node>(
    maze: Array<IntArray>,
    private val scorer: ScorerKotlin<T>,
    private val startField: T,
    private val endField: T
) {

    private val allFields: HashMap<T, RouteNodeKotlin<T>> = HashMap()
    private val openSet: Queue<RouteNodeKotlin<T>> = PriorityQueue()
    private val mazeUtil: MazeUtilKotlin<T>

    init {
        this.mazeUtil = MazeUtilKotlin(maze)
    }

    /**
     * A*Search algorithm which searches for the path from the beginning to the end field. It stops when find the route
     * or when there is nor route at all (went through all nodes).
     *
     * @return all fields from the beginning to the end of the path / route.
     */
    fun findPathTo(): List<T> {
        val startFieldRoute =
            RouteNodeKotlin(startField, null, 0.0, scorer.computeCost(startField, endField))
        allFields[startField] = startFieldRoute
        openSet.add(startFieldRoute)

        while (!openSet.isEmpty()) {
            println(
                "Open Set contains: " +
                        openSet.stream().map(RouteNodeKotlin<T>::current).collect(Collectors.toSet())
            )
            val currentFieldRoute = openSet.poll()
            println("*** Looking at node: $currentFieldRoute")

            //Exit routine when the end / destination field is found.
            if (currentFieldRoute.current == endField) {
                return getFieldPath(allFields, currentFieldRoute)
            }

            val currentField = currentFieldRoute.current
            val currentRouteScore = currentFieldRoute.routeScore
            val neighbourFields = mazeUtil.getNeighbourFields(currentFieldRoute.current)

            for (neighbourField in neighbourFields) {
                val distanceStartToNeighbourField =
                    currentRouteScore + scorer.computeCost(currentField, neighbourField)
                if (!allFields.containsKey(neighbourField)) {
                    addNewFieldRoute(currentField, neighbourField, distanceStartToNeighbourField)
                } else {
                    val neighbourFieldRoute = allFields[neighbourField]
                    if (neighbourFieldRoute!!.routeScore > distanceStartToNeighbourField) {
                        updateFieldRoute(
                            neighbourFieldRoute,
                            currentField,
                            neighbourField,
                            distanceStartToNeighbourField
                        )
                    }
                }
            }
        }

        throw IllegalStateException("No route found")
    }

    /**
     * Updating the values of the neighbourFieldRoute because shorter path to this field was found.
     *
     * @param currentField the current field from which the neighbour field comes.
     * @param neighbourField one of the neighbour of the current field.
     * @param distanceStartToNeighbourField the distance from the first / beginning field to this one.
     */
    private fun updateFieldRoute(
        neighbourFieldRoute: RouteNodeKotlin<T>, currentField: T, neighbourField: T,
        distanceStartToNeighbourField: Double
    ) {
        println(
            "Shorter path found for node with position ($neighbourField?.x, $neighbourField?.y). " +
                    "Was $neighbourFieldRoute.routeScore will be $distanceStartToNeighbourField"
        )
        neighbourFieldRoute.previous = currentField
        neighbourFieldRoute.routeScore = distanceStartToNeighbourField
        neighbourFieldRoute.estimatedScore =
            distanceStartToNeighbourField + scorer.computeCost(neighbourField, this.endField)
        neighbourFieldRoute.current.g = (currentField.g.plus(neighbourFieldRoute.current.w))
        openSet.add(neighbourFieldRoute)
        println("Found a better route to node: $neighbourFieldRoute")
    }

    /**
     * Adds new route to the route list because this node was never visited before.
     *
     * @param currentField the current field from which the neighbour field comes.
     * @param neighbourField one of the neighbour of the current field.
     * @param distanceStartToNeighbourField the distance from the first / beginning field to this one.
     */
    private fun addNewFieldRoute(currentField: T, neighbourField: T, distanceStartToNeighbourField: Double) {
        val newFieldRoute = RouteNodeKotlin(
            neighbourField,
            currentField,
            distanceStartToNeighbourField,
            distanceStartToNeighbourField + this.scorer.computeCost(neighbourField, this.endField)
        )
        this.openSet.add(newFieldRoute)
        this.allFields[neighbourField] = newFieldRoute
    }

    /**
     * Creates and returns list of all fields of the path from the beginning to the end / destination field.
     *
     * @param allFields         all fields
     * @param currentFieldRoute last field which is the destination fields as well.
     * @return all fields of the path from the beginning to the end / destination field.
     */
    private fun getFieldPath(
        allFields: Map<T, RouteNodeKotlin<T>>, currentFieldRoute: RouteNodeKotlin<T>
    ): List<T> {
        println("Found our destination!")
        val route = ArrayList<T>()
        var current = currentFieldRoute
        do {
            val currentField = current.current
            route.add(0, currentField)
            //"previous" is for the while-condition necessary and for bounding the previous nodes for describing
            //the route from the start to the destination.
            if (allFields.containsKey(current.previous)) {
                current = allFields[current.previous]!!
            } else {
                println("Route: $route")
                return route
            }
        } while (true)
    }

}
