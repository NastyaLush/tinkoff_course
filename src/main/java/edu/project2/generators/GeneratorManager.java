package edu.project2;

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
