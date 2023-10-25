package edu.project2.generators;

import edu.project2.gameObjects.Maze;

public interface Generator {

    Maze generate(Integer rows, Integer columns);
}
