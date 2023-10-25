package edu.project2.generators;

import edu.project2.gameObjects.RBCell;
import edu.project2.gameObjects.Maze;
import edu.project2.gameObjects.RBCell;
import edu.project2.util.Util;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Objects;

public class RecursiveBacktracker implements Generator {

    private final Deque<RBCell> stack;
    private Maze<RBCell> maze;

    public RecursiveBacktracker() {
        this.stack = new ArrayDeque<>();
    }

    @Override
    public Maze<RBCell> generate(Integer rows, Integer columns) {
        maze = new Util().getSimpleFullMaze(rows, columns);
        RBCell begin = maze.getMaze()[0][0];
        begin.setVisited(true);
        stack.addFirst(begin);
        RBCell curent;
        RBCell chosen;
        while (!stack.isEmpty()) {
            curent = stack.getFirst();
            ArrayList<RBCell> freeNeighbors = getFreeNeighbors(curent.getRow(), curent.getColumn());
            if (freeNeighbors.isEmpty()) {
                stack.removeFirst();
            } else {
                chosen = choseRandom(freeNeighbors);
                chosen.setVisited(true);
                stack.addFirst(chosen);
                breakWall(curent, chosen);
            }
        }
        return maze;
    }

    private ArrayList<RBCell> getFreeNeighbors(Integer row, Integer column) {
        ArrayList<RBCell> freeCells = new ArrayList<>();
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

    private RBCell choseRandom(ArrayList<RBCell> freeCells) {
        int pointer = (int) Math.floor(Math.random() * freeCells.size());
        return freeCells.get(pointer);
    }

    private void breakWall(RBCell parent, RBCell child) {
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
