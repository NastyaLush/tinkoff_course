package edu.project2.generators;

import edu.project2.gameObjects.Maze;
import org.jetbrains.annotations.NotNull;

public interface Generator {

    Maze generate(@NotNull Integer rows, @NotNull Integer columns);

    default void validateData(@NotNull Integer rows, @NotNull Integer columns) {
        if (rows <= 0 || columns <= 0) {
            throw new IllegalArgumentException();
        }
    }
}
