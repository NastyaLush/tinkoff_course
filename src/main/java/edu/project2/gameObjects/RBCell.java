package edu.project2.gameObjects;

public class RBCell extends Cell {

    private boolean visited;

    public RBCell(Integer row, Integer column, boolean leftWall, boolean bottomWall) {
        super(row, column, leftWall, bottomWall);
        this.visited = false;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}
