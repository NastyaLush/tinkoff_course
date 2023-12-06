package edu.hw9.task3;

import edu.project2.gameObjects.Cell;
import edu.project2.gameObjects.Maze;
import java.util.Set;
import org.junit.jupiter.api.Test;
import static edu.project2.MazeTestGenerator.generateCells;
import static edu.project2.MazeTestGenerator.getPath;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConcurrencyDepthSearchTest {

    @Test
    public void solve_shouldFindPath() {
        Cell begin = new Cell(0, 0, true, false);
        Cell end = new Cell(2, 2, true, true);
        Set<Cell> expectedPath = getPath();

        Set<Cell> actualPath = new ConcurrencyDepthSearch().solve(new Maze<>(generateCells(), 3, 3), begin, end);

        assertEquals(expectedPath, actualPath);
    }
}
