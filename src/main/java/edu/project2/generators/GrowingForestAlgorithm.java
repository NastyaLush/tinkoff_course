package edu.project2.generators;

import edu.project2.gameObjects.GPACell;
import edu.project2.gameObjects.GPAStatus;
import edu.project2.gameObjects.Maze;
import edu.project2.util.Util;
import java.util.ArrayList;
import java.util.Objects;

public class GrowingForestAlgorithm implements Generator {

    private final ArrayList<GPACell> newCells;
    private final ArrayList<GPACell> activeCells;
    private Maze<GPACell> maze;

    public GrowingForestAlgorithm() {
        this.newCells = new ArrayList<>();
        this.activeCells = new ArrayList<>();
    }

    @Override
    public Maze generate(Integer rows, Integer columns) {
        maze = new Util().getSimpleFullMazeGPA(rows, columns);
        int counter = 0;
        for (int i = 0; i < maze.getRows(); i++) {
            for (int j = 0; j < maze.getColumns(); j++) {
                maze.getMaze()[i][j].setStatus(GPAStatus.NEW);
                maze.getMaze()[i][j].setClassNumber(counter++);
                newCells.add(maze.getMaze()[i][j]);
            }
        }
        int begin = getRandomNumber(1, (int) Math.max(1, rows * columns * 0.05));
        for (int i = 0; i < begin; i++) {
            GPACell cell = choseRandom(newCells);
            cell.setStatus(GPAStatus.ACTIVE);
            newCells.remove(cell);
            activeCells.add(cell);
        }
        while (!activeCells.isEmpty()) {
            GPACell cell = activeCells.get(getRandomNumber(0, activeCells.size() - 1));
            ArrayList<GPACell> freeNeigh = getFreeNeighbors(cell.getRow(), cell.getColumn());

            if (freeNeigh.isEmpty()) {
                cell.setStatus(GPAStatus.FINISHED);
                activeCells.remove(cell);
            } else {
                GPACell cell1 = choseRandom(freeNeigh);
                breakWall(cell, cell1);
                if (cell1.getStatus() == GPAStatus.NEW) {
                    cell1.setStatus(GPAStatus.ACTIVE);
                    cell1.setClassNumber(cell.getClassNumber());
                    newCells.remove(cell1);
                    activeCells.add(cell1);
                } else {
                    changeClass(cell, cell1);
                }
            }
        }

        return maze;
    }

    private Integer getRandomNumber(Integer from, Integer till) {
        return (int) Math.floor(Math.random() * (till - from) + from);
    }
  
    private GPACell choseRandom(ArrayList<GPACell> freeCells) {
        int pointer = (int) Math.floor(Math.random() * freeCells.size());
        return freeCells.get(pointer);
    }

    private ArrayList<GPACell> getFreeNeighbors(Integer row, Integer column) {
        ArrayList<GPACell> freeCells = new ArrayList<>();
        if (row > 0 && maze.getMaze()[row - 1][column].getStatus() != GPAStatus.FINISHED &&
            !Objects.equals(
                maze.getMaze()[row - 1][column].getClassNumber(),
                maze.getMaze()[row][column].getClassNumber()
            )) {
            freeCells.add(maze.getMaze()[row - 1][column]);
        }
        if (row < maze.getRows() - 1 && maze.getMaze()[row + 1][column].getStatus() != GPAStatus.FINISHED &&
            !Objects.equals(
                maze.getMaze()[row + 1][column].getClassNumber(),
                maze.getMaze()[row][column].getClassNumber()
            )) {
            freeCells.add(maze.getMaze()[row + 1][column]);
        }
        if (column > 0 && maze.getMaze()[row][column - 1].getStatus() != GPAStatus.FINISHED && !Objects.equals(
            maze.getMaze()[row][column - 1].getClassNumber(),
            maze.getMaze()[row][column].getClassNumber()
        )) {
            freeCells.add(maze.getMaze()[row][column - 1]);
        }
        if (column < maze.getColumns() - 1 && maze.getMaze()[row][column + 1].getStatus() != GPAStatus.FINISHED &&
            !Objects.equals(
                maze.getMaze()[row][column + 1].getClassNumber(),
                maze.getMaze()[row][column].getClassNumber()
            )) {
            freeCells.add(maze.getMaze()[row][column + 1]);
        }
        return freeCells;
    }

    private void breakWall(GPACell parent, GPACell child) {
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

    private void changeClass(GPACell parent, GPACell child) {
        Integer classForRemove = child.getClassNumber();
        for (GPACell cell : activeCells) {
            if (cell.getClassNumber() == classForRemove) {
                cell.setClassNumber(parent.getClassNumber());
            }
        }
    }

    private ArrayList<GPACell> getNeighbors(Integer row, Integer column) {
        ArrayList<GPACell> freeCells = new ArrayList<>();
        if (row > 0 &&
            Objects.equals(
                maze.getMaze()[row - 1][column].getClassNumber(),
                maze.getMaze()[row][column].getClassNumber()
            )) {
            freeCells.add(maze.getMaze()[row - 1][column]);
        }
        if (row < maze.getRows() - 1 && Objects.equals(
            maze.getMaze()[row + 1][column].getClassNumber(),
            maze.getMaze()[row][column].getClassNumber()
        )) {
            freeCells.add(maze.getMaze()[row + 1][column]);
        }
        if (column > 0 && Objects.equals(
            maze.getMaze()[row][column - 1].getClassNumber(),
            maze.getMaze()[row][column].getClassNumber()
        )) {
            freeCells.add(maze.getMaze()[row][column - 1]);
        }
        if (column < maze.getColumns() - 1 && Objects.equals(
            maze.getMaze()[row][column + 1].getClassNumber(),
            maze.getMaze()[row][column].getClassNumber()
        )) {
            freeCells.add(maze.getMaze()[row][column + 1]);
        }
        return freeCells;
    }
}
