package edu.project2;

import edu.project2.gameObjects.Cell;
import java.util.HashSet;
import java.util.Set;

public class MazeTestGenerator {

    protected static Cell[][] generateCells() {
        Cell[][] cells = new Cell[3][3];
        cells[0][0] = new Cell(0, 0, true, false);
        cells[0][1] = new Cell(0, 1, false, true);
        cells[0][2] = new Cell(0, 2, false, true);
        cells[1][0] = new Cell(1, 0, true, false);
        cells[1][1] = new Cell(1, 1, true, false);
        cells[1][2] = new Cell(1, 2, false, false);
        cells[2][0] = new Cell(2, 0, true, true);
        cells[2][1] = new Cell(2, 1, false, true);
        cells[2][2] = new Cell(2, 2, true, true);

        return cells;
    }

    protected static Set<Cell> getPath() {
        Set<Cell> path = new HashSet<>();
        path.add(new Cell(0, 0, true, false));
        path.add(new Cell(1, 0, true, false));
        path.add(new Cell(2, 0, true, true));
        path.add(new Cell(1, 1, true, false));
        path.add(new Cell(1, 2, false, false));
        path.add(new Cell(2, 1, false, true));
        path.add(new Cell(2, 2, true, true));

        return path;
    }
}
