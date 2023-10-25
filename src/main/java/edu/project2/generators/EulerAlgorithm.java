package edu.project2.generators;

import edu.project2.gameObjects.Cell;
import edu.project2.gameObjects.Maze;
import edu.project2.util.Util;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class EulerAlgorithm implements Generator {

    private final HashMap<Integer, ArrayList<Cell>> cellsOfCurrentRowWithoutBottomByClasses;
    private Maze<Cell> maze;
    private Integer[] classesOfCurrentRowCells;

    private Integer counter;

    public EulerAlgorithm() {
        this.cellsOfCurrentRowWithoutBottomByClasses = new HashMap<>();
        this.counter = 0;
    }

    @Override
    public Maze<Cell> generate(Integer rows, Integer columns) {
        maze = new Util().getSimpleEmptyMaze(rows, columns);
        generateStartClasses(maze.getColumns());

        for (int row = 0; row < maze.getRows(); row++) {
            regenerateClasses(row - 1, maze);
            updateCellsOfCurrentRowWithoutBottomByClasses(maze, row);

            for (int column = 0; column < maze.getColumns() - 1; column++) {
                if (isSameClass(column, column + 1) || shouldCreateWall()) {
                    createLeftWall(maze.getMaze()[row][column + 1]);
                } else {
                    mergeClasses(column, column + 1);
                }
            }
            for (int column = 0; column < maze.getColumns(); column++) {
                if (isAloneWithFreeBottomInThisClass(classesOfCurrentRowCells[column])) {
                    continue;
                }
                if (shouldCreateWall()) {
                    createBottomWall(maze.getMaze()[row][column]);
                    removeCellWithBottomFromMap(row, column, maze);
                }
            }

        }

        addBottomWallsToLastRow(maze);
        addLeftWallsToFirstColumn(maze);

        correctLeftWallsInLastRow(maze);
        return maze;
    }

    private void generateStartClasses(Integer columns) {
        classesOfCurrentRowCells = new Integer[columns];
        for (int i = 0; i < columns; i++) {
            classesOfCurrentRowCells[i] = counter++;
            cellsOfCurrentRowWithoutBottomByClasses.put(classesOfCurrentRowCells[i], new ArrayList<>());
        }

    }

    private void regenerateClasses(Integer row, Maze maze) {
        if (row < 0) {
            return;
        }
        for (int i = 0; i < maze.getColumns(); i++) {
            if (maze.getMaze()[row][i].isBottomWall()) {
                classesOfCurrentRowCells[i] = counter++;
                cellsOfCurrentRowWithoutBottomByClasses.put(classesOfCurrentRowCells[i], new ArrayList<>());
            }
        }
    }

    private void updateCellsOfCurrentRowWithoutBottomByClasses(Maze maze, Integer row) {

        for (Integer key : cellsOfCurrentRowWithoutBottomByClasses.keySet()) {
            cellsOfCurrentRowWithoutBottomByClasses.get(key).clear();
        }
        for (int classNumber = 0; classNumber < maze.getColumns(); classNumber++) {
            cellsOfCurrentRowWithoutBottomByClasses.get(classesOfCurrentRowCells[classNumber])
                .add(maze.getMaze()[row][classNumber]);
        }
    }

    private boolean isSameClass(Integer first, Integer second) {
        return classesOfCurrentRowCells[first].equals(classesOfCurrentRowCells[second]);
    }

    private boolean shouldCreateWall() {
        return Math.random() >= 0.5;
    }

    private void createLeftWall(Cell cell) {
        cell.setLeftWall(true);
    }

    private void mergeClasses(Integer main, Integer secondary) {
        int classForRemove = classesOfCurrentRowCells[secondary];
        cellsOfCurrentRowWithoutBottomByClasses.get(classesOfCurrentRowCells[main]).addAll(
            cellsOfCurrentRowWithoutBottomByClasses.get(classForRemove));
        cellsOfCurrentRowWithoutBottomByClasses.remove(classesOfCurrentRowCells[secondary]);

        updateClassesOfCurrentRowCells(classForRemove, main);
    }

    private boolean isAloneWithFreeBottomInThisClass(Integer setNumberOfCell) {
        return
            cellsOfCurrentRowWithoutBottomByClasses.get(setNumberOfCell).stream().filter((Cell a) -> !a.isBottomWall())
                .count() == 1;
    }

    private void createBottomWall(Cell cell) {
        cell.setBottomWall(true);
    }

    private void removeCellWithBottomFromMap(Integer row, Integer column, Maze maze) {
        cellsOfCurrentRowWithoutBottomByClasses.get(classesOfCurrentRowCells[column])
            .remove(maze.getMaze()[row][column]);
    }

    private void addBottomWallsToLastRow(Maze maze) {
        for (int j = 0; j < maze.getColumns(); j++) {
            createBottomWall(maze.getMaze()[maze.getRows() - 1][j]);
        }
    }

    private void addLeftWallsToFirstColumn(Maze maze) {
        for (int i = 0; i < maze.getRows(); i++) {
            createLeftWall(maze.getMaze()[i][0]);
        }
    }

    private void correctLeftWallsInLastRow(Maze maze) {
        int lastRow = maze.getRows() - 1;
        for (int column = 0; column < maze.getColumns() - 1; column++) {
            removeLeftWallIfDifferentClass(maze, lastRow, column, column + 1);
        }
    }

    private void updateClassesOfCurrentRowCells(Integer classForRemove, Integer column) {
        for (int i = 0; i < classesOfCurrentRowCells.length; i++) {
            if (Objects.equals(classesOfCurrentRowCells[i], classForRemove)) {
                classesOfCurrentRowCells[i] = classesOfCurrentRowCells[column];
            }
        }
    }

    private void removeLeftWallIfDifferentClass(Maze maze, Integer row, Integer first, Integer second) {
        if (!isSameClass(first, second)) {
            breakLeftWall(maze.getMaze()[row][second]);
            updateClassesOfCurrentRowCells(classesOfCurrentRowCells[second], first);
        }
    }

    private void breakLeftWall(Cell cell) {
        cell.setLeftWall(false);
    }

}
