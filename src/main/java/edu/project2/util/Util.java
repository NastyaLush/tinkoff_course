package edu.project2.util;

import edu.project2.gameObjects.Cell;

public class Util {

    public static String getUnderLined(String input) {
        return "\033[4m" + input + "\033[0m";
    }

    public Cell[][] getSimpleFullMaze(int rows, int columns) {
        Cell[][] maze = new Cell[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                maze[i][j] = new Cell(i, j, true, true);
            }
        }
        return maze;
    }

    public Cell[][] getSimpleEmptyMaze(int rows, int columns) {
        Cell[][] maze = new Cell[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                maze[i][j] = new Cell(i, j, false, false);
            }
        }
        return maze;
    }

}
