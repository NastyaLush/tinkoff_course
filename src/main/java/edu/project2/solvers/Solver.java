package edu.project2.solvers;

import edu.project2.gameObjects.Cell;
import edu.project2.gameObjects.Maze;
import java.util.Set;

public interface Solver {

    Set<Cell> solve(Maze<Cell> maze, Cell begin, Cell end);

    default boolean isInMaze(Maze<Cell> maze, Cell cell) {
        return between(cell.getRow(), 0, maze.rows()) && between(cell.getColumn(), 0, maze.columns());
    }

    default boolean between(Integer value, Integer left, Integer right) {
        return value >= left && value <= right;
    }

}
