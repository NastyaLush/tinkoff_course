package edu.hw9.task3;

import edu.project2.gameObjects.Cell;
import edu.project2.gameObjects.DepthCell;
import edu.project2.gameObjects.DepthCellConcurrent;
import edu.project2.gameObjects.Maze;
import edu.project2.solvers.Solver;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ConcurrencyDepthSearch implements Solver {

    DepthCellConcurrent[][] maze;

    @Override
    public Set<Cell> solve(Maze<Cell> maze, Cell begin, Cell end) {
        createAndFillMazeForSolving(maze);
        if (!isInMaze(maze, begin) || !isInMaze(maze, end)) {
            log.error("{} or {} are not in the maze", begin, end);
            throw new IllegalArgumentException();
        }
        try (ForkJoinPool forkJoinPool = new ForkJoinPool()) {
            Set<Cell> cells = forkJoinPool.invoke(new Finder(this.maze, begin, end));
            return cells;
        }
    }

    private void createAndFillMazeForSolving(Maze<Cell> maze) {
        this.maze = new DepthCellConcurrent[maze.rows()][maze.columns()];

        for (int i = 0; i < maze.rows(); i++) {
            for (int j = 0; j < maze.columns(); j++) {
                this.maze[i][j] = new DepthCellConcurrent(maze.maze()[i][j]);
            }
        }
    }

    private ArrayList<DepthCellConcurrent> getFreeNeighbors(DepthCellConcurrent[][] maze, Integer row, Integer column) {
        ArrayList<DepthCellConcurrent> freeCells = new ArrayList<>();
        if (row > 0 && !maze[row - 1][column].isVisited() && !maze[row - 1][column].getCell()
                                                                                   .isBottomWall()) {
            freeCells.add(maze[row - 1][column]);
        }
        if (row < maze.length - 1 && !maze[row + 1][column].isVisited()
            && !maze[row][column].getCell()
                                 .isBottomWall()) {
            freeCells.add(maze[row + 1][column]);
        }
        if (column > 0 && !maze[row][column - 1].isVisited() && !maze[row][column].getCell()
                                                                                  .isLeftWall()) {
            freeCells.add(maze[row][column - 1]);
        }
        if (column < maze[0].length - 1 && !maze[row][column + 1].isVisited()
            && !maze[row][column + 1].getCell()
                                     .isLeftWall()) {
            freeCells.add(maze[row][column + 1]);
        }
        return freeCells;
    }

    class Finder extends RecursiveTask<Set<Cell>> {

        private final DepthCellConcurrent[][] maze;
        private final Cell begin;
        private final Cell end;
        private final List<Finder> finders = new ArrayList<>();

        Finder(DepthCellConcurrent[][] maze, Cell begin, Cell end) {
            this.maze = maze;
            this.begin = begin;
            this.end = end;
        }

        @Override
        protected Set<Cell> compute() {
            Set<Cell> pathSet = new TreeSet<>();
            if (begin.equals(end)) {
                pathSet.add(begin);
                return pathSet;
            }
            for (DepthCell neighbor : getFreeNeighbors(maze, begin.getRow(), begin.getColumn())) {
                neighbor.setVisited(true);
                finders.add(new Finder(maze, neighbor.getCell(), end));
            }
            for (Finder finder : finders) {
                finder.fork();
                Set<Cell> cells = finder.join();
                if (!cells.isEmpty()) {
                    pathSet.addAll(cells);
                    pathSet.add(begin);
                    return pathSet;
                }
            }
            return pathSet;
        }
    }
}
