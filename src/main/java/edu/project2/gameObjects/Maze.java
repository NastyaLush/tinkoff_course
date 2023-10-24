package edu.project2.gameObjects;

import edu.project2.util.Util;

public class Maze {

    private final Cell[][] maze;
    private final Integer rows;
    private final Integer columns;

    public Maze(Integer columns, Integer rows) {
        this.maze = new Util().getSimpleEmptyMaze(columns, rows);
        this.rows = rows;
        this.columns = columns;
    }

    public Cell[][] getMaze() {
        return maze;
    }

    public Integer getRows() {
        return rows;
    }

    public Integer getColumns() {
        return columns;
    }
}
