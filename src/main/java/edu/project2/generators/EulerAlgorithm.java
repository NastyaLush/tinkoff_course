package edu.project2.generators;

import edu.project2.gameObjects.Cell;
import edu.project2.gameObjects.Maze;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

public class EulerAlgorithm implements Generator {

    private static final double PROBABILITY_OF_BUILDING_A_WALL = 0.5;
    private final HashMap<Integer, ArrayList<Cell>> cellsOfCurrentRowWithoutBottomByClasses;
    private Maze<Cell> maze;
    private Integer[] classesOfCurrentRowCells;
    private double probabilityOfBuildingAWall;
    private Integer counter;

    public EulerAlgorithm() {
        this.cellsOfCurrentRowWithoutBottomByClasses = new HashMap<>();
        this.counter = 0;
        this.probabilityOfBuildingAWall = PROBABILITY_OF_BUILDING_A_WALL;
    }

    @Override
    public Maze<Cell> generate(@NotNull Integer rows, @NotNull Integer columns) {
        LOGGER.info("start generate maze using euler algorithm");
        validateData(rows, columns);
        createMaze(rows, columns);
        generateStartClasses(maze.columns());

        for (int row = 0; row < maze.rows(); row++) {
            regenerateClasses(row - 1, maze);
            updateCellsOfCurrentRowWithoutBottomByClasses(maze, row);

            for (int column = 0; column < maze.columns() - 1; column++) {
                if (isSameClass(column, column + 1) || shouldCreateWall()) {
                    createLeftWall(maze.maze()[row][column + 1]);
                } else {
                    mergeClasses(column, column + 1);
                }
            }
            for (int column = 0; column < maze.columns(); column++) {
                if (isAloneWithFreeBottomInThisClass(classesOfCurrentRowCells[column])) {
                    continue;
                }
                if (shouldCreateWall()) {
                    createBottomWall(maze.maze()[row][column]);
                    removeCellWithBottomFromMap(row, column, maze);
                }
            }

        }

        addBottomWallsToLastRow(maze);
        addLeftWallsToFirstColumn(maze);

        correctLeftWallsInLastRow(maze);
        LOGGER.info("finish generate maze using euler algorithm");
        return maze;
    }

    private void createMaze(Integer rows, Integer columns) {
        Cell[][] mazeArray = new Cell[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                mazeArray[i][j] = new Cell(i, j, false, false);
            }
        }
        this.maze = new Maze<>(mazeArray, rows, columns);
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
        for (int i = 0; i < maze.columns(); i++) {
            if (maze.maze()[row][i].isBottomWall()) {
                classesOfCurrentRowCells[i] = counter++;
                cellsOfCurrentRowWithoutBottomByClasses.put(classesOfCurrentRowCells[i], new ArrayList<>());
            }
        }
    }

    private void updateCellsOfCurrentRowWithoutBottomByClasses(Maze maze, Integer row) {

        for (Integer key : cellsOfCurrentRowWithoutBottomByClasses.keySet()) {
            cellsOfCurrentRowWithoutBottomByClasses.get(key)
                                                   .clear();
        }
        for (int classNumber = 0; classNumber < maze.columns(); classNumber++) {
            cellsOfCurrentRowWithoutBottomByClasses.get(classesOfCurrentRowCells[classNumber])
                                                   .add(maze.maze()[row][classNumber]);
        }
    }

    private boolean isSameClass(Integer first, Integer second) {
        return classesOfCurrentRowCells[first].equals(classesOfCurrentRowCells[second]);
    }

    private boolean shouldCreateWall() {
        return Math.random() >= probabilityOfBuildingAWall;
    }

    private void createLeftWall(Cell cell) {
        cell.setLeftWall(true);
    }

    private void mergeClasses(Integer main, Integer secondary) {
        int classForRemove = classesOfCurrentRowCells[secondary];
        cellsOfCurrentRowWithoutBottomByClasses.get(classesOfCurrentRowCells[main])
                                               .addAll(
                                                       cellsOfCurrentRowWithoutBottomByClasses.get(classForRemove));
        cellsOfCurrentRowWithoutBottomByClasses.remove(classesOfCurrentRowCells[secondary]);

        updateClassesOfCurrentRowCells(classForRemove, main);
    }

    private boolean isAloneWithFreeBottomInThisClass(Integer setNumberOfCell) {
        return
                cellsOfCurrentRowWithoutBottomByClasses.get(setNumberOfCell)
                                                       .stream()
                                                       .filter((Cell a) -> !a.isBottomWall())
                                                       .count() == 1;
    }

    private void createBottomWall(Cell cell) {
        cell.setBottomWall(true);
    }

    private void removeCellWithBottomFromMap(Integer row, Integer column, Maze maze) {
        cellsOfCurrentRowWithoutBottomByClasses.get(classesOfCurrentRowCells[column])
                                               .remove(maze.maze()[row][column]);
    }

    private void addBottomWallsToLastRow(Maze maze) {
        for (int j = 0; j < maze.columns(); j++) {
            createBottomWall(maze.maze()[maze.rows() - 1][j]);
        }
    }

    private void addLeftWallsToFirstColumn(Maze maze) {
        for (int i = 0; i < maze.rows(); i++) {
            createLeftWall(maze.maze()[i][0]);
        }
    }

    private void correctLeftWallsInLastRow(Maze maze) {
        int lastRow = maze.rows() - 1;
        for (int column = 0; column < maze.columns() - 1; column++) {
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
            breakLeftWall(maze.maze()[row][second]);
            updateClassesOfCurrentRowCells(classesOfCurrentRowCells[second], first);
        }
    }

    private void breakLeftWall(Cell cell) {
        cell.setLeftWall(false);
    }

    public void setProbabilityOfBuildingAWall(double probabilityOfBuildingAWall) {
        this.probabilityOfBuildingAWall = probabilityOfBuildingAWall;
    }

}
