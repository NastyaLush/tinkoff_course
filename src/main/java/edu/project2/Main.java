package edu.project2;

import edu.project2.gameObjects.Maze;
import edu.project2.generators.EulerAlgorithm;
import edu.project2.generators.GeneratorManager;
import edu.project2.render.BeautyRender;

public class Main {

    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static void printMaze() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.printf("%3d", 1);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        new BeautyRender().rend(new GeneratorManager(new Maze(5, 5
        )).generateMaze(new EulerAlgorithm()));

    }

}
