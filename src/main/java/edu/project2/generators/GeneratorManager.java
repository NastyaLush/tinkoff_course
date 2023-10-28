package edu.project2.generators;

import edu.project2.gameObjects.Maze;
import org.jetbrains.annotations.NotNull;

public class GeneratorManager {

    private final Integer rows;
    private final Integer columns;

    public GeneratorManager(@NotNull Integer rows, @NotNull Integer columns) {
        if (rows <= 0 || columns <= 0) {
            throw new IllegalArgumentException();
        }
        this.rows = rows;
        this.columns = columns;
    }

    public Maze generateMaze(@NotNull Generator generator) {
        return generator.generate(rows, columns);
    }

}
