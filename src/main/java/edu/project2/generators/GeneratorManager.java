package edu.project2.generators;

import edu.project2.gameObjects.Maze;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

@Slf4j
public class GeneratorManager {

    private final Integer rows;
    private final Integer columns;

    public GeneratorManager(@NotNull Integer rows, @NotNull Integer columns) {
        if (rows <= 0 || columns <= 0) {
            log.error("invalid data rows {} columns {}", rows, columns);
            throw new IllegalArgumentException();
        }
        this.rows = rows;
        this.columns = columns;
    }

    public Maze generateMaze(@NotNull Generator generator) {
        log.info("generate maze using {}", generator);
        return generator.generate(rows, columns);

    }

}
