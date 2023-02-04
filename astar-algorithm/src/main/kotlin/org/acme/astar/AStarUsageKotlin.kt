package org.acme.astar

// -1 = blocked
// 0+ = additional movement cost
val maze = arrayOf(
    intArrayOf(0,   0,   0,   0,   0,   0,   0,   0),
    intArrayOf(0,   0,   0,   0,   0,   0,   0,   0),
    intArrayOf(0,   0,   0, 100, 100, 100,   0,   0),
    intArrayOf(0,   0,   0,   0,   0, 100,   0,   0),
    intArrayOf(0,   0, 100,   0,   0, 100,   0,   0),
    intArrayOf(0,   0, 100,   0,   0, 100,   0,   0),
    intArrayOf(0,   0, 100, 100, 100, 100,   0,   0),
    intArrayOf(0,   0,   0,   0,   0,   0,   0,   0)
)

fun main(args: Array<String>) {
    val ignoreWeight = args.isNotEmpty() && args[0].toBoolean()

    val aStar = AStarKotlin(maze, 0, 0, ignoreWeight)
    val path = aStar.findPathTo(7, 7)
    for (n in path) {
        print("[" + n.x + ", " + n.y + "] ")
        maze[n.y][n.x] = -1
    }

    val formattedNumber: String = "%.2f".format(path[path.size - 1].g)
    println()
    println("Total cost: $formattedNumber")

    for (mazeRow in maze) {
        for (mazeEntry in mazeRow) {
            when (mazeEntry) {
                0 -> print("_")
                -1 -> print("*")
                else -> print("#")
            }
        }
        println()
    }
}
