package edu.project2.solvers;

import edu.project2.gameObjects.Cell;
import edu.project2.gameObjects.Maze;
import java.util.Set;

public class SolverManager {

    private final Maze maze;

    public SolverManager(Maze maze) {
        this.maze = maze;
    }

    public Set<Cell> solveMaze(Solver solver, Cell begin, Cell end) {
        return solver.solve(maze, begin, end);
    }

}
