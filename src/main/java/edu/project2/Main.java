package edu.project2;

import edu.project2.gameObjects.Cell;
import edu.project2.gameObjects.Maze;
import edu.project2.generators.EulerAlgorithm;
import edu.project2.generators.GeneratorManager;
import edu.project2.generators.GrowingForestAlgorithm;
import edu.project2.generators.RecursiveBacktracker;
import edu.project2.render.BeautyRender;
import edu.project2.solvers.AStar;
import edu.project2.solvers.SolverManager;
import java.util.Set;

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
        Maze maze = new GeneratorManager(100, 100).generateMaze(new EulerAlgorithm());
        new BeautyRender().rend(maze);
        Set<Cell> path = new SolverManager(maze).solveMaze(new AStar(), maze.getMaze()[0][0], maze.getMaze()[99][99]);
        new BeautyRender().rendWithPath(maze, path);
    }

}
