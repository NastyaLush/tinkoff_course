package edu.project2.generators;

import edu.project2.gameObjects.Maze;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public interface Generator {

    Logger LOGGER = LogManager.getLogger();

    Maze generate(@NotNull Integer rows, @NotNull Integer columns);

    default void validateData(@NotNull Integer rows, @NotNull Integer columns) {
        if (rows <= 0 || columns <= 0) {
            LOGGER.error("receive invalid data rows {} columns {}", rows, columns);
            throw new IllegalArgumentException();
        }
    }
}
