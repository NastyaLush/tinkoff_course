package edu.project2.render;

import edu.project2.gameObjects.Cell;
import edu.project2.gameObjects.Maze;
import java.util.Set;
import org.jetbrains.annotations.NotNull;

public class RenderManager {

    private final Maze maze;

    public RenderManager(@NotNull Maze maze) {
        this.maze = maze;
    }

    public void renderMaze(@NotNull Render render, @NotNull Input input) {
        render.rend(maze, input);
    }

    public void renderMazeWithPath(@NotNull Render render, @NotNull Set<Cell> path, @NotNull Input input) {
        render.rendWithPath(maze, path, input);
    }

}
