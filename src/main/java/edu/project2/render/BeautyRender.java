package edu.project2.render;

import edu.project2.gameObjects.Cell;
import edu.project2.gameObjects.Maze;

public class BeautyRender implements Render {

    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_RESET = "\u001B[0m";

    public void rend(Maze maze) {
        for (int i = 0; i < maze.getColumns(); i++) {
            System.out.print(ANSI_PURPLE_BACKGROUND + "      " + ANSI_RESET);
        }
        System.out.print(ANSI_PURPLE_BACKGROUND + "   " + ANSI_RESET);
        System.out.print("\n");
        for (int i = 0; i < maze.getRows(); i++) {

            for (int j = 0; j < maze.getColumns(); j++) {
                getFirstLevelCell(maze.getMaze()[i][j]);
            }
            System.out.print(ANSI_PURPLE_BACKGROUND + "   " + ANSI_RESET + "\n");
            for (int j = 0; j < maze.getRows(); j++) {
                getSecondLevelCell(maze.getMaze()[i][j]);
            }

            System.out.print(ANSI_PURPLE_BACKGROUND + "   " + ANSI_RESET + "\n");
        }
    }

    public void getFirstLevelCell(Cell cell) {
        if (cell.isLeftWall()) {
            System.out.print(ANSI_PURPLE_BACKGROUND + "   " + ANSI_RESET + "   ");
        } else {
            System.out.print("   " + "   ");
        }
    }

    public void getSecondLevelCell(Cell cell) {
        System.out.print(ANSI_PURPLE_BACKGROUND + "   " + ANSI_RESET);
        if (cell.isBottomWall()) {
            System.out.print(ANSI_PURPLE_BACKGROUND + "   " + ANSI_RESET);
        } else {
            System.out.print("   ");
        }
    }

}
