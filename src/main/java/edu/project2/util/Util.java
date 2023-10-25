package edu.project2.util;

import edu.project2.gameObjects.Cell;
import edu.project2.gameObjects.GPACell;
import edu.project2.gameObjects.GPAStatus;
import edu.project2.gameObjects.Maze;
import edu.project2.gameObjects.RBCell;

public class Util {

    public static String getUnderLined(String input) {
        return "\033[4m" + input + "\033[0m";
    }

    public Maze<RBCell> getSimpleFullMaze(int rows, int columns) {
        RBCell[][] maze = new RBCell[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                maze[i][j] = new RBCell(i, j, true, true);
            }
        }
        return new Maze<>(rows, columns, maze);
    }

    public Maze<Cell> getSimpleEmptyMaze(int rows, int columns) {
        Cell[][] maze = new Cell[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                maze[i][j] = new Cell(i, j, false, false);
            }
        }
        return new Maze<>(rows, columns, maze);
    }

    public Maze<GPACell> getSimpleFullMazeGPA(int rows, int columns) {
        GPACell[][] maze = new GPACell[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                maze[i][j] = new GPACell(i, j, true, true);
            }
        }
        return new Maze<>(rows, columns, maze);
    }

}
