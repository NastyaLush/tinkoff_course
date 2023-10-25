package edu.project2.render;

import edu.project2.gameObjects.Cell;
import edu.project2.gameObjects.Maze;
import java.util.Set;

public interface Render {

    <T extends Cell> void rend(Maze<T> maze, Input input);

    <T extends Cell> void rendWithPath(Maze<T> maze, Set<Cell> path, Input input);
}
