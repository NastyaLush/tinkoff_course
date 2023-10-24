package edu.project2.generators;

import edu.project2.gameObjects.Cell;
import edu.project2.gameObjects.Maze;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Objects;

public class RecursiveBacktracker implements Generator {

    private final Deque<Cell> stack;

    public RecursiveBacktracker() {
        this.stack = new ArrayDeque<>();
    }

    @Override
    public void generate(Maze maze) {
        Cell begin = maze.getMaze()[0][0];
        begin.setVisited(true);
        stack.addFirst(begin);
        Cell curent;
        Cell chosen;
        while (!stack.isEmpty()) {
            curent = stack.getFirst();
            ArrayList<Cell> freeNeighbors = getFreeNeighbors(maze, curent.getRow(), curent.getColumn());
            if (freeNeighbors.isEmpty()) {
                stack.removeFirst();
            } else {
                chosen = choseRandom(freeNeighbors);
                chosen.setVisited(true);
                stack.addFirst(chosen);
                breakWall(curent, chosen);
            }
        }
    }

    private ArrayList<Cell> getFreeNeighbors(Maze maze, Integer row, Integer column) {
        ArrayList<Cell> freeCells = new ArrayList<>();
        if (row > 0 && !maze.getMaze()[row - 1][column].isVisited()) {
            freeCells.add(maze.getMaze()[row - 1][column]);
        }
        if (row < maze.getRows() - 1 && !maze.getMaze()[row + 1][column].isVisited()) {
            freeCells.add(maze.getMaze()[row + 1][column]);
        }
        if (column > 0 && !maze.getMaze()[row][column - 1].isVisited()) {
            freeCells.add(maze.getMaze()[row][column - 1]);
        }
        if (column < maze.getColumns() - 1 && !maze.getMaze()[row][column + 1].isVisited()) {
            freeCells.add(maze.getMaze()[row][column + 1]);
        }
        return freeCells;
    }

    private Cell choseRandom(ArrayList<Cell> freeCells) {
        int pointer = (int) Math.floor(Math.random() * freeCells.size());
        return freeCells.get(pointer);
    }

    private void breakWall(Cell parent, Cell child) {
        if (Objects.equals(parent.getColumn(), child.getColumn())) {
            if (parent.getRow() < child.getRow()) {
                parent.setBottomWall(false);
            } else {
                child.setBottomWall(false);
            }
        }
        if (Objects.equals(parent.getRow(), child.getRow())) {
            if (parent.getColumn() > child.getColumn()) {
                parent.setLeftWall(false);
            } else {
                child.setLeftWall(false);
            }
        }
    }
}
