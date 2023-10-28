package edu.project2.gameObjects;

import org.jetbrains.annotations.NotNull;

public record Maze<T extends Cell>(T[][] maze, Integer rows, Integer columns) {

    public Maze(@NotNull T[][] maze, @NotNull Integer rows, @NotNull Integer columns) {
        this.maze = maze;
        if (rows <= 0 || columns <= 0 || rows != maze.length || columns != maze[0].length) {
            throw new IllegalArgumentException();
        }
        this.rows = rows;
        this.columns = columns;
    }
}
