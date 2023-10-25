package edu.project2.render;

import edu.project2.gameObjects.Cell;
import edu.project2.gameObjects.Maze;
import java.util.Set;

public class RenderManager {

    private final Maze maze;

    public RenderManager(Maze maze) {
        this.maze = maze;
    }

    public void renderMaze(Render render, Input input) {
        render.rend(maze, input);
    }

    public void renderMazeWithPath(Render render, Set<Cell> path, Input input) {
        render.rendWithPath(maze, path, input);
    }

}
