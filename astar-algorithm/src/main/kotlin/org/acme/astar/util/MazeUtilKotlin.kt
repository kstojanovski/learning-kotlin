package org.acme.astar.util

import org.acme.astar.AStarKotlin

class MazeUtilKotlin<T : AStarKotlin.Node?>(val maze: Array<IntArray>) {

    /**
     * Find all neighbour fields of the current one and return in set with them.
     *
     * @param currentField the field on which the neighbour nodes are found.
     * @return all neighbour nodes.
     */
    fun getNeighbourFields(currentField: T): HashSet<T> {
        val neighbourFields = HashSet<T>()
        val fieldPosX = currentField?.x
        val fieldPosY = currentField?.y
        val lengthX = fieldPosX?.plus(1)
        val lengthY = fieldPosY?.plus(1)

        if (fieldPosX != null && fieldPosY != null) {
            for (x in (fieldPosX - 1)..lengthX!!) {
                for (y in (fieldPosY - 1)..lengthY!!) {
                    if ((x != fieldPosX || y != fieldPosY) //do not put the current node into the set.
                        && (x > -1) && (x < 8) //do not put node into the set if the X position is out of the table.
                        && (y > -1) && (y < 8)) { //do not put node into the set if the Y position is out of the table.
                        @Suppress("UNCHECKED_CAST") val aStarKotlinNode = AStarKotlin.Node(x, y, maze[y][x]) as T
                        neighbourFields.add(aStarKotlinNode)
                    }
                }
            }
        }
        return neighbourFields
    }
}
