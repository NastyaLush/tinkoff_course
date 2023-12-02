package edu.project2;

import static edu.project2.MazeTestGenerator.generateCells;
import static edu.project2.MazeTestGenerator.getPath;
import edu.project2.gameObjects.Cell;
import edu.project2.gameObjects.Maze;
import edu.project2.render.BeautyRender;
import edu.project2.render.RenderManager;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Set;

public class BeautyWikiRendererSingleThreadedTest {

    @Test
    public void rend_shouldRendCorrectly() {
        Cell[][] cells = generateCells();
        Maze maze = new Maze<>(cells, 3, 3);
        RenderManager renderManager = new RenderManager(maze);
        String expectedMaze = "\u001B[45m      \u001B[0m\u001B[45m      "
            + "\u001B[0m\u001B[45m      \u001B[0m\u001B[45m   "
            + "\u001B[0m\u001B[45m   \u001B[0m               \u001B[45m   "
            + "\u001B[0m\u001B[45m   \u001B[0m   \u001B[45m   \u001B[0m\u001B[45m   "
            + "\u001B[0m\u001B[45m   \u001B[0m\u001B[45m   \u001B[0m\u001B[45m   "
            + "\u001B[0m\u001B[45m   \u001B[0m   \u001B[45m   \u001B[0m         "
            + "\u001B[45m   \u001B[0m\u001B[45m   \u001B[0m   \u001B[45m   "
            + "\u001B[0m   \u001B[45m   \u001B[0m   \u001B[45m   \u001B[0m\u001B[45m   "
            + "\u001B[0m         \u001B[45m   \u001B[0m   \u001B[45m   "
            + "\u001B[0m\u001B[45m   \u001B[0m\u001B[45m   \u001B[0m\u001B[45m   "
            + "\u001B[0m\u001B[45m   \u001B[0m\u001B[45m   \u001B[0m\u001B[45m   "
            + "\u001B[0m\u001B[45m   \u001B[0m";
        final String[] actualMaze = {""};

        renderManager.renderMaze(new BeautyRender(), string -> actualMaze[0] += string);

        assertEquals(expectedMaze, actualMaze[0]);

    }

    @Test
    public void rendWithPath_shouldWorkCorrectlyIfPathIsEmpty() {
        BeautyRender beautyRender = new BeautyRender();
        Maze<Cell> maze = new Maze<>(generateCells(), 3, 3);
        Set<Cell> path = new HashSet<>();

        beautyRender.rendWithPath(maze, path, (data) -> {
        });
    }

    @Test
    public void rendWithPath_shouldWorkCorrectlyIfPathContainsCellsThatNotExist() {
        BeautyRender beautyRender = new BeautyRender();
        Maze<Cell> maze = new Maze<>(generateCells(), 3, 3);
        Set<Cell> path = new HashSet<>();
        path.add(new Cell(5, 5, true, true));
        path.add(new Cell(1, 1, true, true));

        beautyRender.rendWithPath(maze, path, (data) -> {
        });
    }

    @Test
    public void rendWithPath_shouldRendCorrectly() {
        Cell[][] cells = generateCells();
        Maze maze = new Maze<>(cells, 3, 3);
        RenderManager renderManager = new RenderManager(maze);
        String expectedMaze =
            "\u001B[45m      \u001B[0m\u001B[45m      " + "\u001B[0m\u001B[45m      \u001B[0m\u001B[45m   "
                + "\u001B[0m\u001B[45m   \u001B[0m #             "
                + "\u001B[45m   \u001B[0m\u001B[45m   \u001B[0m   "
                + "\u001B[45m   \u001B[0m\u001B[45m   \u001B[0m\u001B[45m   "
                + "\u001B[0m\u001B[45m   \u001B[0m\u001B[45m   \u001B[0m\u001B[45m   "
                + "\u001B[0m # \u001B[45m   \u001B[0m #     # \u001B[45m   "
                + "\u001B[0m\u001B[45m   \u001B[0m   \u001B[45m   \u001B[0m   "
                + "\u001B[45m   \u001B[0m   \u001B[45m   \u001B[0m\u001B[45m   "
                + "\u001B[0m #     # \u001B[45m   \u001B[0m # \u001B[45m   "
                + "\u001B[0m\u001B[45m   \u001B[0m\u001B[45m   \u001B[0m\u001B[45m   "
                + "\u001B[0m\u001B[45m   \u001B[0m\u001B[45m   \u001B[0m\u001B[45m   "
                + "\u001B[0m\u001B[45m   \u001B[0m";
        final String[] actualMaze = {""};

        renderManager.renderMazeWithPath(new BeautyRender(), getPath(), string -> actualMaze[0] += string);

        assertEquals(expectedMaze, actualMaze[0]);

    }

}
