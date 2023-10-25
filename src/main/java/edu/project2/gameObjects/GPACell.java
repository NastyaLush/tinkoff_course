package edu.project2.gameObjects;

public class GPACell extends Cell {

    private GPAStatus status;
    private Integer classNumber;

    public GPACell(Integer row, Integer column, boolean leftWall, boolean bottomWall) {
        super(row, column, leftWall, bottomWall);
    }

    public GPAStatus getStatus() {
        return status;
    }

    public void setStatus(GPAStatus status) {
        this.status = status;
    }

    public Integer getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(Integer classNumber) {
        this.classNumber = classNumber;
    }
}
