package edu.project2.render;

import edu.project2.gameObjects.Cell;
import edu.project2.gameObjects.Maze;
import java.util.Set;
import org.jetbrains.annotations.NotNull;

public interface Render {

    <T extends Cell> void rend(@NotNull Maze<T> maze, @NotNull Input input);

    <T extends Cell> void rendWithPath(@NotNull Maze<T> maze, @NotNull Set<Cell> path, @NotNull Input input);
}
