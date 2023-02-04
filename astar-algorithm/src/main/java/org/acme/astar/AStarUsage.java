package org.acme.astar;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class AStarUsage {

    // -1 = blocked
    // 0+ = additional movement cost
    static public int[][] maze = {
            {0, 0,   0,   0,   0,   0, 0, 0},
            {0, 0,   0,   0,   0,   0, 0, 0},
            {0, 0,   0, 100, 100, 100, 0, 0},
            {0, 0,   0,   0,   0, 100, 0, 0},
            {0, 0, 100,   0,   0, 100, 0, 0},
            {0, 0, 100,   0,   0, 100, 0, 0},
            {0, 0, 100, 100, 100, 100, 0, 0},
            {0, 0,   0,   0,   0,   0, 0, 0},
    };

    public static void main(String[] args) {
        boolean ignoreWeight = args.length != 0 && Boolean.parseBoolean(args[0]);

        AStar as = new AStar(maze, 0, 0, ignoreWeight);
        List<AStar.Node> path = as.findPathTo(7, 7);
        if (path != null) {
            path.forEach((n) -> {
                System.out.print("[" + n.getX() + ", " + n.getY() + "] ");
                maze[n.getY()][n.getX()] = -1;
            });
            System.out.printf("\nTotal cost: %.02f\n", path.get(path.size() - 1).getG());

            for (int[] maze_row : maze) {
                for (int maze_entry : maze_row) {
                    switch (maze_entry) {
                        case 0 -> System.out.print("_");
                        case -1 -> System.out.print("*");
                        default -> System.out.print("#");
                    }
                }
                System.out.println();
            }
        }
    }

}
