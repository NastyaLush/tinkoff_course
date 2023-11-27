package edu.project2.generators;

import edu.project2.gameObjects.Cell;
import edu.project2.gameObjects.Maze;
import java.util.ArrayList;
import java.util.Objects;

public abstract class TreeGenerator implements Generator {

    protected abstract void createAndFillSimpleMaze(Integer rows, Integer columns);

    protected <T> T choseRandom(ArrayList<T> freeCells) {
        int pointer = getRandomNumber(0, freeCells.size());
        return freeCells.get(pointer);
    }

    protected Integer getRandomNumber(Integer from, Integer till) {
        return (int) Math.floor(Math.random() * (till - from) + from);
    }

    protected <T extends Cell> void breakWall(T parent, T child) {
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

    protected <T extends Cell> ArrayList<T> getFreeNeighbors(Integer row, Integer column, Maze<T> maze) {
        ArrayList<T> freeCells = new ArrayList<>();
        if (isNeighbor(row, column, row - 1, column)) {
            freeCells.add(maze.maze()[row - 1][column]);
        }
        if (isNeighbor(row, column, row + 1, column)) {
            freeCells.add(maze.maze()[row + 1][column]);
        }
        if (isNeighbor(row, column, row, column - 1)) {
            freeCells.add(maze.maze()[row][column - 1]);
        }
        if (isNeighbor(row, column, row, column + 1)) {
            freeCells.add(maze.maze()[row][column + 1]);
        }
        return freeCells;
    }

    protected abstract boolean isNeighbor(
        Integer rowCurrent,
        Integer columnCurrent,
        Integer rowPotential,
        Integer columnPotential
    );
}
