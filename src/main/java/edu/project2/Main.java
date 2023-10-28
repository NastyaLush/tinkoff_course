package edu.project2;

import edu.project2.gameObjects.Cell;
import edu.project2.gameObjects.Maze;
import edu.project2.generators.GeneratorManager;
import edu.project2.generators.GrowingForestAlgorithm;
import edu.project2.render.BeautyRender;
import edu.project2.render.RenderManager;
import edu.project2.solvers.AStarSolver;
import edu.project2.solvers.SolverManager;
import java.util.Set;

public class Main {

    private final static int DEFAULT_ROWS = 100;
    private final static int DEFAULT_COLUMNS = 100;
    private final static int DEFAULT_BEGIN_CELL_ROW = 0;
    private final static int DEFAULT_BEGIN_CELL_COLUMN = 0;
    private final static int DEFAULT_END_CELL_ROW = 99;
    private final static int DEFAULT_END_CELL_COLUMN = 99;

    private Main() {
    }

    public static void main(String[] args) {

        Maze maze = new GeneratorManager(DEFAULT_ROWS, DEFAULT_COLUMNS).generateMaze(new GrowingForestAlgorithm());
        Set<Cell> path = new SolverManager(maze)
                .solveMaze(new AStarSolver(),
                        maze.maze()[DEFAULT_BEGIN_CELL_ROW][DEFAULT_BEGIN_CELL_COLUMN],
                        maze.maze()[DEFAULT_END_CELL_ROW][DEFAULT_END_CELL_COLUMN]);
        new RenderManager(maze).renderMazeWithPath(new BeautyRender(), path, System.out::println);
    }

}
