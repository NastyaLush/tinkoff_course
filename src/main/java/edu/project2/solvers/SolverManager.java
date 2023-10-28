package edu.project2.solvers;

import edu.project2.gameObjects.Cell;
import edu.project2.gameObjects.Maze;
import java.util.Set;
import org.jetbrains.annotations.NotNull;

public class SolverManager {

    private final Maze<Cell> maze;

    public SolverManager(@NotNull Maze<Cell> maze) {
        this.maze = maze;
    }

    public Set<Cell> solveMaze(@NotNull Solver solver, @NotNull Cell begin, @NotNull Cell end) {
        return solver.solve(maze, begin, end);
    }

}
