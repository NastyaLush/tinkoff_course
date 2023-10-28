package edu.project2.solvers;

import edu.project2.gameObjects.Cell;
import edu.project2.gameObjects.Maze;
import java.util.Set;

public interface Solver {

    Set<Cell> solve(Maze<Cell> maze, Cell begin, Cell end);
}
