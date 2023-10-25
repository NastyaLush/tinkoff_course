package edu.project2.generators;

import edu.project2.gameObjects.Maze;
import edu.project2.gameObjects.RecursiveBacktrackerCell;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class RecursiveBacktracker extends TreeGenerator {

    private final Deque<RecursiveBacktrackerCell> stack;
    private Maze<RecursiveBacktrackerCell> maze;
    private RecursiveBacktrackerCell startCell;

    public RecursiveBacktracker() {
        this.stack = new ArrayDeque<>();
    }

    @Override
    public Maze<RecursiveBacktrackerCell> generate(Integer rows, Integer columns) {
        createAndFillSimpleMaze(rows, columns);
        if (startCell == null) {
            startCell = maze.maze()[0][0];
        }
        startCell.setVisited(true);
        stack.addFirst(startCell);

        RecursiveBacktrackerCell current;
        RecursiveBacktrackerCell chosen;

        while (!stack.isEmpty()) {
            current = stack.getFirst();
            ArrayList<RecursiveBacktrackerCell> freeNeighbors =
                getFreeNeighbors(current.getRow(), current.getColumn(), maze);

            if (freeNeighbors.isEmpty()) {
                stack.removeFirst();
            } else {
                chosen = choseRandom(freeNeighbors);
                chosen.setVisited(true);
                stack.addFirst(chosen);
                breakWall(current, chosen);
            }
        }
        return maze;
    }

    @Override
    protected void createAndFillSimpleMaze(Integer rows, Integer columns) {
        RecursiveBacktrackerCell[][] mazeRB = new RecursiveBacktrackerCell[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                mazeRB[i][j] = new RecursiveBacktrackerCell(i, j, true, true);
            }
        }
        maze = new Maze<>(mazeRB, rows, columns);

    }

    @Override
    protected boolean isNeighbor(
        Integer rowCurrent,
        Integer columnCurrent,
        Integer rowPotential,
        Integer columnPotential
    ) {
        return rowCurrent >= 0 && rowPotential >= 0
            && columnCurrent >= 0 && columnPotential >= 0
            && rowCurrent < maze.rows() && rowPotential < maze.rows()
            && columnCurrent < maze.columns() && columnPotential < maze.columns()
            && !maze.maze()[rowPotential][columnPotential].isVisited();
    }

    public void setStartCell(RecursiveBacktrackerCell startCell) {
        this.startCell = startCell;
    }
}
