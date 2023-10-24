package edu.project2.render;

import edu.project2.gameObjects.Maze;
import edu.project2.generators.Generator;

public class RenderManager {

    private final Maze maze;

    public RenderManager(Maze maze) {
        this.maze = maze;
    }

    public void renderMaze(Render render) {
        render.rend(maze);
    }

}
