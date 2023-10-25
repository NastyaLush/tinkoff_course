package edu.project2.gameObjects;

import edu.project2.util.Util;

public class Maze<T extends Cell> {

    private final T[][] maze;
    private final Integer rows;
    private final Integer columns;

    public Maze(Integer rows, Integer columns, T[][] maze) {
        this.maze = maze;
        this.rows = rows;
        this.columns = columns;
    }

    public T[][] getMaze() {
        return maze;
    }

    public Integer getRows() {
        return rows;
    }

    public Integer getColumns() {
        return columns;
    }
}
