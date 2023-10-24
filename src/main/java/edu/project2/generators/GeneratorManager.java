package edu.project2.generators;

import edu.project2.gameObjects.Maze;
import edu.project2.generators.Generator;

public class GeneratorManager {

    private final Maze maze;

    public GeneratorManager(Maze maze) {
        this.maze = maze;
    }

    public Maze generateMaze(Generator generator) {
        generator.generate(maze);
        return maze;
    }

}
