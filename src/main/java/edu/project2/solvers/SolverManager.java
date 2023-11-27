package edu.project2.solvers;

import edu.project2.gameObjects.Cell;
import edu.project2.gameObjects.Maze;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

@Slf4j
public class SolverManager {

    private final Maze<Cell> maze;

    public SolverManager(@NotNull Maze<Cell> maze) {
        this.maze = maze;
    }

    public Set<Cell> solveMaze(@NotNull Solver solver, @NotNull Cell begin, @NotNull Cell end) {
        log.info("start solving maze using {}", solver);
        return solver.solve(maze, begin, end);
    }

}
