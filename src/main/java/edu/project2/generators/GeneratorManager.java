package edu.project2.generators;

import edu.project2.gameObjects.Maze;

public class GeneratorManager {

    private final Integer rows;
    private final Integer columns;

    public GeneratorManager(Integer rows, Integer columns) {
        this.rows = rows;
        this.columns = columns;
    }

    public Maze generateMaze(Generator generator) {
        return generator.generate(rows, columns);
    }

}
