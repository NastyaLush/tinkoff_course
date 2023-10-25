package edu.project2.generators;

import edu.project2.gameObjects.GrowingForestAlgorithmCell;
import edu.project2.gameObjects.GrowingForestAlgorithmStatus;
import edu.project2.gameObjects.Maze;
import java.util.ArrayList;
import java.util.Objects;

public class GrowingForestAlgorithm extends TreeGenerator {

    private static final double PERCENT_OF_CELLS_THAT_ADDS_IN_THE_BEGIN_DEFAULT = 0.05;
    private final ArrayList<GrowingForestAlgorithmCell> newCells;
    private final ArrayList<GrowingForestAlgorithmCell> activeCells;
    private double percentOfCellsThatAddsInTheBegin;
    private Maze<GrowingForestAlgorithmCell> maze;

    public GrowingForestAlgorithm() {
        this.newCells = new ArrayList<>();
        this.activeCells = new ArrayList<>();
        this.percentOfCellsThatAddsInTheBegin = PERCENT_OF_CELLS_THAT_ADDS_IN_THE_BEGIN_DEFAULT;
    }

    @Override
    public Maze generate(Integer rows, Integer columns) {
        createAndFillSimpleMaze(rows, columns);

        addStatusNewAndUniqueClassToCells();

        makeSomeRandomCellsActive();

        while (!activeCells.isEmpty()) {
            GrowingForestAlgorithmCell parent = getRandomActiveCell();
            ArrayList<GrowingForestAlgorithmCell> freeNeighbors =
                getFreeNeighbors(parent.getRow(), parent.getColumn(), this.maze);

            if (freeNeighbors.isEmpty()) {
                parent.setStatus(GrowingForestAlgorithmStatus.FINISHED);
                activeCells.remove(parent);
            } else {
                GrowingForestAlgorithmCell child = choseRandom(freeNeighbors);
                breakWall(parent, child);
                mergeClassesOfCells(parent, child);
            }
        }

        return maze;
    }

    @Override
    protected void createAndFillSimpleMaze(Integer rows, Integer columns) {
        GrowingForestAlgorithmCell[][] mazeArray = new GrowingForestAlgorithmCell[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                mazeArray[i][j] = new GrowingForestAlgorithmCell(i, j, true, true);
            }
        }
        this.maze = new Maze<>(mazeArray, rows, columns);

    }

    private void addStatusNewAndUniqueClassToCells() {
        int counter = 0;
        for (int i = 0; i < maze.rows(); i++) {
            for (int j = 0; j < maze.columns(); j++) {
                maze.maze()[i][j].setStatus(GrowingForestAlgorithmStatus.NEW);
                maze.maze()[i][j].setClassNumber(counter++);
                newCells.add(maze.maze()[i][j]);
            }
        }
    }

    private void makeSomeRandomCellsActive() {
        int begin =
            getRandomNumber(1, (int) Math.max(1, maze.rows() * maze.columns() * percentOfCellsThatAddsInTheBegin));
        for (int i = 0; i < begin; i++) {
            GrowingForestAlgorithmCell cell = choseRandom(newCells);
            cell.setStatus(GrowingForestAlgorithmStatus.ACTIVE);
            newCells.remove(cell);
            activeCells.add(cell);
        }
    }

    private GrowingForestAlgorithmCell getRandomActiveCell() {
        return activeCells.get(getRandomNumber(0, activeCells.size() - 1));
    }

    private void mergeClassesOfCells(GrowingForestAlgorithmCell parent, GrowingForestAlgorithmCell child) {
        if (child.getStatus() == GrowingForestAlgorithmStatus.NEW) {
            child.setStatus(GrowingForestAlgorithmStatus.ACTIVE);
            child.setClassNumber(parent.getClassNumber());
            newCells.remove(child);
            activeCells.add(child);
        } else {
            changeClass(parent, child);
        }
    }

    private void changeClass(GrowingForestAlgorithmCell parent, GrowingForestAlgorithmCell child) {
        Integer classForRemove = child.getClassNumber();
        for (GrowingForestAlgorithmCell cell : activeCells) {
            if (cell.getClassNumber() == classForRemove) {
                cell.setClassNumber(parent.getClassNumber());
            }
        }
    }

    @Override
    protected boolean isNeighbor(
        Integer rowCurrent, Integer columnCurrent, Integer rowPotential, Integer columnPotential
    ) {
        return rowCurrent >= 0 && rowPotential >= 0
            && columnCurrent >= 0 && columnPotential >= 0
            && rowCurrent < maze.rows() && rowPotential < maze.rows()
            && columnCurrent < maze.columns() && columnPotential < maze.columns()
            && maze.maze()[rowPotential][columnPotential].getStatus() != GrowingForestAlgorithmStatus.FINISHED
            && !Objects.equals(
            maze.maze()[rowPotential][columnPotential].getClassNumber(),
            maze.maze()[rowCurrent][columnCurrent].getClassNumber()
        );
    }

    public void setPercentOfCellsThatAddsInTheBegin(double percentOfCellsThatAddsInTheBegin) {
        this.percentOfCellsThatAddsInTheBegin = percentOfCellsThatAddsInTheBegin;
    }
}
