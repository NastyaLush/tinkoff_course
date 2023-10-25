package edu.project2.gameObjects;

import java.util.Objects;

public class Cell {

    private final Integer row;
    private final Integer column;

    private boolean leftWall;
    private boolean bottomWall;

    public Cell(Integer row, Integer column, boolean leftWall, boolean bottomWall) {
        this.row = row;
        this.column = column;
        this.leftWall = leftWall;
        this.bottomWall = bottomWall;
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

    @Override public String toString() {
        return "Cell{" +
            "row=" + row +
            ", column=" + column +
            ", leftWall=" + leftWall +
            ", bottomWall=" + bottomWall +
            '}';
    }

    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Cell cell = (Cell) o;
        return leftWall == cell.leftWall && bottomWall == cell.bottomWall && Objects.equals(row, cell.row) &&
            Objects.equals(column, cell.column);
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column, leftWall, bottomWall);
    }
}
