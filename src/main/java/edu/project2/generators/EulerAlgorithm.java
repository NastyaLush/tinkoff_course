package edu.project2.generators;

import edu.project2.gameObjects.Cell;
import edu.project2.gameObjects.Maze;
import edu.project2.render.BeautyRender;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

public class EulerAlgorithm implements Generator {

    private final HashMap<Integer, ArrayList<Cell>> setValueCounter;

    private Integer counter;

    public EulerAlgorithm() {
        this.setValueCounter = new HashMap<>();
        this.counter = 0;
    }

    @Override
    public void generate(Maze maze) {
        Integer[] set = generateSetString(maze.getColumns());
        for (int i = 0; i < maze.getRows(); i++) {

            for (int j = 0; j < set.length; j++) {
                setValueCounter.get(set[j]).clear();
            }
            for (int j = 0; j < maze.getColumns(); j++) {
                setValueCounter.get(set[j]).add(maze.getMaze()[i][j]);
            }

            for (int j = 0; j < maze.getColumns() - 1; j++) {
                if (Objects.equals(set[j], set[j + 1]) || shouldCreateWall()) {
                    createLeftWall(maze.getMaze()[i][j + 1]);
                } else {
                    setValueCounter.get(set[j]).addAll(setValueCounter.get(set[j + 1]));
                    setValueCounter.remove(set[j + 1]);
                    set[j + 1] = set[j];
                }
            }
            for (int j = 0; j < maze.getColumns(); j++) {
                if (isAloneWithFreeBottomInThisClass(set, set[j])) {
                    continue;
                }
                if (shouldCreateWall()) {
                    createBottomWall(maze.getMaze()[i][j]);
                    setValueCounter.get(set[j]).remove(maze.getMaze()[i][j]);
                }
            }
            regenerateSet(set, i, maze);

        }

        for (int j = 0; j < maze.getColumns(); j++) {
            createBottomWall(maze.getMaze()[maze.getRows() - 1][j]);
        }
        for (int i = 0; i < maze.getRows(); i++) {
            createLeftWall(maze.getMaze()[i][0]);
        }

        for (int i = 0; i < maze.getColumns(); i++) {
            System.out.print(set[i] + " ");
        }
        System.out.println();

        new BeautyRender().rend(maze);
        int i = maze.getRows() - 1;
        for (int j = 0; j < maze.getColumns() - 1; j++) {
            if (!Objects.equals(set[j], set[j + 1])) {
                breakLeftWall(maze.getMaze()[i][j + 1]);
                set[j + 1] = set[j];
            }
        }

    }

    private Integer[] generateSetString(Integer columns) {
        Integer[] emptySet = new Integer[columns];
        for (int i = 0; i < columns; i++) {
            emptySet[i] = counter++;
            setValueCounter.put(emptySet[i], new ArrayList<>());
        }
        return emptySet;
    }

    private boolean shouldCreateWall() {
        return Math.random() >= 0.5;
    }

    private void createLeftWall(Cell cell) {
        cell.setLeftWall(true);
    }

    private boolean isAloneWithFreeBottomInThisClass(Integer[] set, Integer setNumberOfCell) {
        return setValueCounter.get(setNumberOfCell).stream().filter((Cell a) -> !a.isBottomWall()).count() == 1;
    }

    private void createBottomWall(Cell cell) {
        cell.setBottomWall(true);
    }

    private void regenerateSet(Integer[] set, Integer row, Maze maze) {
        for (int i = 0; i < maze.getColumns(); i++) {
            if (maze.getMaze()[row][i].isBottomWall()) {
                set[i] = counter++;
                setValueCounter.put(set[i], new ArrayList<>());
            }
        }
    }

    private void breakLeftWall(Cell cell) {
        cell.setLeftWall(false);
    }

}
