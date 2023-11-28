package edu.project2;

import edu.project2.gameObjects.Cell;
import edu.project2.gameObjects.Maze;
import edu.project2.generators.Generator;
import edu.project2.generators.GeneratorManager;
import edu.project2.solvers.Solver;
import edu.project2.solvers.SolverManager;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.HashSet;
import java.util.Set;

public class GeneratorManagerTest {

    @Test
    public void solveMaze_shouldCallSolveMethod() {
        Generator generator = Mockito.mock(Generator.class);
        Maze<Cell> maze = new Maze<>(MazeTestGenerator.generateCells(), 3, 3);
        GeneratorManager generatorManager = new GeneratorManager(1, 1);
        Mockito.when(generator.generate(1, 1))
               .thenReturn(maze);

        generatorManager.generateMaze(generator);

        Mockito.verify(generator, Mockito.times(1))
               .generate(1, 1);
    }


}
