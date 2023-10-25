package edu.project2.gameObjects;

public class SolvedMaze extends Maze {

    private final AStarCell[][] solvedCell;

    public SolvedMaze(Maze maze, AStarCell[][] solvedCell) {
        super(maze.getRows(), maze.getColumns(), maze.getMaze());
        this.solvedCell = solvedCell;
    }

    public AStarCell[][] getSolvedCell() {
        return solvedCell;
    }
}
