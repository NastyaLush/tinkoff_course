package edu.project2;

import edu.project2.gameObjects.Cell;
import edu.project2.gameObjects.Maze;
import edu.project2.render.Input;
import edu.project2.render.Render;
import edu.project2.render.RenderManager;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.HashSet;
import java.util.Set;

public class WikiRendererSingleThreadedManagerTest {

    @Test
    public void renderMaze_shouldCallRendMethod() {
        Render render = Mockito.mock(Render.class);
        Input input = Mockito.mock(Input.class);
        Maze<Cell> maze = new Maze<>(MazeTestGenerator.generateCells(), 3, 3);
        RenderManager renderManager = new RenderManager(maze);

        renderManager.renderMaze(render, input);

        Mockito.verify(render, Mockito.times(1))
               .rend(maze, input);
    }

    @Test
    public void renderMazeWithPath_shouldRendWithPath() {
        Render render = Mockito.mock(Render.class);
        Input input = Mockito.mock(Input.class);
        Set<Cell> givenSetCell = new HashSet<>();
        givenSetCell.add(Mockito.any());
        givenSetCell.add(Mockito.any());
        givenSetCell.add(Mockito.any());
        Maze<Cell> maze = new Maze<>(MazeTestGenerator.generateCells(), 3, 3);
        RenderManager renderManager = new RenderManager(maze);

        renderManager.renderMazeWithPath(render, givenSetCell, input);

        Mockito.verify(render, Mockito.times(1))
               .rendWithPath(maze, givenSetCell, input);
    }

}
