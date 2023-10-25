package edu.project2.gameObjects;

public record Maze<T extends Cell>(T[][] maze, Integer rows, Integer columns) {

}
