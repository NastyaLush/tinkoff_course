package edu.project2.solvers;

import edu.project2.gameObjects.AStarCell;
import edu.project2.gameObjects.Cell;
import edu.project2.gameObjects.Maze;
import edu.project2.gameObjects.SolvedMaze;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class AStar implements Solver {

    PriorityQueue<AStarCell> queue;
    AStarCell[][] maze;
    private AStarCell begin;
    private AStarCell end;

    public AStar() {
        this.queue = new PriorityQueue<>(new Comparator<AStarCell>() {
            @Override
            public int compare(AStarCell first, AStarCell second) {
                return first.getWeight().compareTo(second.getWeight());
            }
        });
    }

    @Override
    public Set<Cell> solve(Maze maze, Cell begin, Cell end) {
        this.maze = new AStarCell[maze.getRows()][maze.getColumns()];

        for (int i = 0; i < maze.getRows(); i++) {
            for (int j = 0; j < maze.getColumns(); j++) {
                this.maze[i][j] = new AStarCell(maze.getMaze()[i][j]);
            }
        }
        this.begin = this.maze[begin.getRow()][begin.getColumn()];
        this.end = this.maze[end.getRow()][end.getColumn()];

        queue.add(this.begin);
        AStarCell parent = null;
        AStarCell current = this.begin;
        long weight = h(current);
        current.setWeight(weight);
        while (!current.equals(this.end)) {
            current = queue.peek();
            queue.remove();
            if (current.isVisited()) {
                continue;
            }
            current.setVisited(true);
            current.setVisited(true);
            ArrayList<AStarCell> neighbors =
                getFreeNeighbors(this.maze, current.getCell().getRow(), current.getCell().getColumn());
            for (AStarCell neighbor : neighbors) {
                if (neighbor.getWeight() > f(current, neighbor)) {
                    neighbor.setWeight(f(current, neighbor));
                    neighbor.setParent(current);
                }
                queue.add(neighbor);
            }
            parent = current;
        }
        Set<Cell> path = new HashSet<>();
        while (!current.equals(this.begin)) {
            path.add(current.getCell());
            current = current.getParent();
        }
        path.add(current.getCell());
        return path;
    }

    private int h(AStarCell x) {
        return Math.abs(x.getCell().getRow() - end.getCell().getRow()) +
            Math.abs(x.getCell().getColumn() - end.getCell().getColumn());
    }

    private ArrayList<AStarCell> getFreeNeighbors(AStarCell[][] maze, Integer row, Integer column) {
        ArrayList<AStarCell> freeCells = new ArrayList<>();
        if (row > 0 && !maze[row - 1][column].isVisited() && !maze[row - 1][column].getCell().isBottomWall()) {
            freeCells.add(maze[row - 1][column]);
        }
        if (row < maze.length - 1 && !maze[row + 1][column].isVisited() &&
            !maze[row][column].getCell().isBottomWall()) {
            freeCells.add(maze[row + 1][column]);
        }
        if (column > 0 && !maze[row][column - 1].isVisited() && !maze[row][column].getCell().isLeftWall()) {
            freeCells.add(maze[row][column - 1]);
        }
        if (column < maze[0].length - 1 && !maze[row][column + 1].isVisited() &&
            !maze[row][column + 1].getCell().isLeftWall()) {
            freeCells.add(maze[row][column + 1]);
        }
        return freeCells;
    }

    private long f(AStarCell x, AStarCell y) {
        return g(x) + h(y);
    }

    private long g(AStarCell parent) {
        return parent.getWeight() + 1;
    }
}
