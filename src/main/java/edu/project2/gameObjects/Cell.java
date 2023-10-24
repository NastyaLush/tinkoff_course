package edu.project2.gameObjects;

public class Cell {

    private final Integer row;
    private final Integer column;

    private boolean visited;
    private boolean leftWall;
    private boolean bottomWall;

    public Cell(Integer row, Integer column, boolean leftWall, boolean bottomWall) {
        this.row = row;
        this.column = column;
        this.leftWall = leftWall;
        this.bottomWall = bottomWall;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public Integer getRow() {
        return row;
    }

    public Integer getColumn() {
        return column;
    }

    public boolean isLeftWall() {
        return leftWall;
    }

    public void setLeftWall(boolean leftWall) {
        this.leftWall = leftWall;
    }

    public boolean isBottomWall() {
        return bottomWall;
    }

    public void setBottomWall(boolean bottomWall) {
        this.bottomWall = bottomWall;
    }

}
