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

    public static void main(String[] args) {

        Maze maze = new GeneratorManager(100, 100).generateMaze(new GrowingForestAlgorithm());
//        new BeautyRender().rend(maze);
        Set<Cell> path = new SolverManager(maze).solveMaze(new AStarSolver(), maze.maze()[0][0], maze.maze()[99][99]);
//        new BeautyRender().rendWithPath(maze, path);
        new RenderManager(maze).renderMazeWithPath(new BeautyRender(), path, System.out::println);
    }

}
