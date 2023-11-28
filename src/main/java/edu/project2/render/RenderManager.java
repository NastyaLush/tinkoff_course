package edu.project2.render;

import edu.project2.gameObjects.Cell;
import edu.project2.gameObjects.Maze;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

@Slf4j
public class RenderManager {

    private final Maze maze;

    public RenderManager(@NotNull Maze maze) {
        this.maze = maze;
    }

    public void renderMaze(@NotNull Render render, @NotNull Input input) {
        render.rend(maze, input);
    }

    public void renderMazeWithPath(@NotNull Render render, @NotNull Set<Cell> path, @NotNull Input input) {
        log.info("start rendering maze using {}", render);
        render.rendWithPath(maze, path, input);
        log.info("finish rendering maze using {}", render);
    }

}
