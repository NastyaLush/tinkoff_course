package edu.project2.solvers;

import edu.project2.gameObjects.Maze;
import edu.project2.render.Render;

public class SolverManager {

    private final Maze maze;

    public SolverManager(Maze maze) {
        this.maze = maze;
    }

    public void solveMaze(Solver solver) {
        solver.solve(maze);
    }

}
