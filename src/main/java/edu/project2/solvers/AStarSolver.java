package edu.project2.solvers;

import edu.project2.gameObjects.AStarSolverCell;
import edu.project2.gameObjects.Cell;
import edu.project2.gameObjects.Maze;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeSet;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

@Slf4j
public class AStarSolver implements Solver {

    PriorityQueue<AStarSolverCell> queue;
    AStarSolverCell[][] maze;
    private AStarSolverCell start;
    private AStarSolverCell finish;

    public AStarSolver() {
        this.queue = new PriorityQueue<>(Comparator.comparing(AStarSolverCell::getWeight));
    }

    @Override
    public TreeSet<Cell> solve(@NotNull Maze<Cell> maze, @NotNull Cell begin, @NotNull Cell end) {
        log.info("start finding path from {} to {} using A* solver", begin, end);
        createAndFillMazeForSolving(maze);
        if (!isInMaze(maze, begin) || !isInMaze(maze, end)) {
            log.error("{} or {} are not in the maze", begin, end);
            throw new IllegalArgumentException();
        }

        this.start = this.maze[begin.getRow()][begin.getColumn()];
        this.finish = this.maze[end.getRow()][end.getColumn()];

        queue.add(this.start);

        AStarSolverCell current = this.start;
        long weight = h(current);
        current.setWeight(weight);

        while (!current.equals(this.finish)) {
            current = queue.peek();
            if (current == null) {
                log.error("there is no path between {} and {}, please check maze", begin, end);
                throw new IllegalArgumentException();
            }
            queue.remove();
            if (current.isVisited()) {
                continue;
            }
            current.setVisited(true);

            ArrayList<AStarSolverCell> neighbors =
                getFreeNeighbors(this.maze, current.getCell()
                                                   .getRow(), current.getCell()
                                                                     .getColumn());
            for (AStarSolverCell neighbor : neighbors) {
                if (neighbor.getWeight() > f(current, neighbor)) {
                    neighbor.setWeight(f(current, neighbor));
                    neighbor.setParent(current);
                }
                queue.add(neighbor);
            }
        }
        log.info("finish finding path from {} to {} using A* solver", begin, end);
        return getPath(current);
    }

    private void createAndFillMazeForSolving(Maze<Cell> maze) {
        this.maze = new AStarSolverCell[maze.rows()][maze.columns()];

        for (int i = 0; i < maze.rows(); i++) {
            for (int j = 0; j < maze.columns(); j++) {
                this.maze[i][j] = new AStarSolverCell(maze.maze()[i][j]);
            }
        }
    }

    private int h(AStarSolverCell cell) {
        return Math.abs(cell.getCell()
                            .getRow() - finish.getCell()
                                              .getRow())
            + Math.abs(cell.getCell()
                           .getColumn() - finish.getCell()
                                                .getColumn());
    }

    private ArrayList<AStarSolverCell> getFreeNeighbors(AStarSolverCell[][] maze, Integer row, Integer column) {
        ArrayList<AStarSolverCell> freeCells = new ArrayList<>();
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

    private long f(AStarSolverCell parent, AStarSolverCell child) {
        return g(parent) + h(child);
    }

    private TreeSet<Cell> getPath(AStarSolverCell end) {
        AStarSolverCell current = end;
        TreeSet<Cell> path = new TreeSet<>();
        while (!current.equals(this.start)) {
            path.add(current.getCell());
            current = current.getParent();
        }
        path.add(current.getCell());
        return path;
    }

    private long g(AStarSolverCell parent) {
        return parent.getWeight() + 1;
    }
}
