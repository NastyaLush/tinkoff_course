package edu.project2;

import static edu.project2.MazeTestGenerator.generateCells;
import static edu.project2.MazeTestGenerator.getPath;
import edu.project2.gameObjects.Cell;
import edu.project2.gameObjects.Maze;
import edu.project2.solvers.AStarSolver;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Set;

public class AStarSolverTest {

    @Test
    public void solve_shouldThrowExceptionIfDataIncorrect() {
        Cell begin = new Cell(1, 1, false, false);
        Cell end = new Cell(5, 5, false, false);

        assertThrows(IllegalArgumentException.class, () -> new AStarSolver().solve(new Maze<>(generateCells(), 3, 3), begin, end));
    }


    @Test
    public void solve_shouldFindPathIfStartAndFinishTheSame() {
        Cell begin = new Cell(1, 1, true, false);
        Cell end = new Cell(1, 1, false, false);
        Set<Cell> expectedPath = new HashSet<>();
        expectedPath.add(begin);

        Set<Cell> actualPath = new AStarSolver().solve(new Maze<>(generateCells(), 3, 3), begin, end);

        assertEquals(expectedPath, actualPath);
    }

    @Test
    public void solve_shouldFindPath() {
        Cell begin = new Cell(0, 0, true, false);
        Cell end = new Cell(2, 2, true, true);
        Set<Cell> expectedPath = getPath();

        Set<Cell> actualPath = new AStarSolver().solve(new Maze<>(generateCells(), 3, 3), begin, end);

        assertEquals(expectedPath, actualPath);
    }

}
