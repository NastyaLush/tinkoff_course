package edu.project2.solvers;

import edu.project2.gameObjects.Cell;
import edu.project2.gameObjects.Maze;
import edu.project2.gameObjects.SolvedMaze;
import java.util.Set;

public interface Solver {

    public Set<Cell> solve(Maze maze, Cell begin, Cell end);
}
