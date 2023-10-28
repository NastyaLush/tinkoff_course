package edu.project2.render;

import edu.project2.gameObjects.Cell;
import edu.project2.gameObjects.Maze;
import java.util.HashSet;
import java.util.Set;
import org.jetbrains.annotations.NotNull;

public class BeautyRender implements Render {

    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_RESET = "\u001B[0m";

    @Override
    public void rend(@NotNull Maze maze, @NotNull Input input) {
        input.print(rendTopMaze(maze));
        for (int i = 0; i < maze.rows(); i++) {
            input.print(rendFirstLevelCells(maze, i, new HashSet<>()));
            input.print(rendSecondLevelCells(maze, i));
        }
    }

    @Override
    public <T extends Cell> void rendWithPath(@NotNull Maze<T> maze, @NotNull Set<Cell> path, @NotNull Input input) {
        input.print(rendTopMaze(maze));
        for (int i = 0; i < maze.rows(); i++) {
            input.print(rendFirstLevelCells(maze, i, path));
            input.print(rendSecondLevelCells(maze, i));
        }
    }

    private <T extends Cell> String rendTopMaze(Maze<T> maze) {
        StringBuilder stringForPrint = new StringBuilder();
        for (int i = 0; i < maze.columns(); i++) {
            stringForPrint.append(getColoredBackground(getSquare(false) + getSquare(false)));
        }
        stringForPrint.append(getColoredBackground(getSquare(false)));
        return stringForPrint.toString();
    }

    private <T extends Cell> String rendFirstLevelCells(Maze<T> maze, Integer row, Set<Cell> path) {
        StringBuilder stringForPrint = new StringBuilder();
        for (int j = 0; j < maze.columns(); j++) {
            stringForPrint.append(rendFirstLevelCell(maze.maze()[row][j], path.contains(maze.maze()[row][j])));
        }
        stringForPrint.append(getColoredBackground(getSquare(false)));
        return stringForPrint.toString();
    }

    private <T extends Cell> String rendSecondLevelCells(Maze<T> maze, Integer row) {
        StringBuilder stringForPrint = new StringBuilder();
        for (int j = 0; j < maze.columns(); j++) {
            stringForPrint.append(rendSecondLevelCell(maze.maze()[row][j]));
        }
        stringForPrint.append(getColoredBackground(getSquare(false)));
        return stringForPrint.toString();
    }

    private String getColoredBackground(String value) {
        return ANSI_PURPLE_BACKGROUND + value + ANSI_RESET;
    }

    private String getSquare(boolean isOnPath) {
        if (!isOnPath) {
            return "   ";
        } else {
            return " # ";
        }
    }

    private String rendFirstLevelCell(Cell cell, boolean isOnPath) {
        String stringForPrint = "";
        if (cell.isLeftWall()) {
            stringForPrint += getColoredBackground(getSquare(false)) + getSquare(isOnPath);
        } else {
            stringForPrint += getSquare(false) + getSquare(isOnPath);
        }
        return stringForPrint;
    }

    private String rendSecondLevelCell(Cell cell) {
        String stringForPrint = getColoredBackground(getSquare(false));
        if (cell.isBottomWall()) {
            stringForPrint += getColoredBackground(getSquare(false));
        } else {
            stringForPrint += getSquare(false);
        }
        return stringForPrint;
    }

}
