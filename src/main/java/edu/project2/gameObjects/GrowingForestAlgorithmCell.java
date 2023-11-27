package edu.project2.gameObjects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GrowingForestAlgorithmCell extends Cell {

    private GrowingForestAlgorithmStatus status;
    private Integer classNumber;

    public GrowingForestAlgorithmCell(Integer row, Integer column, boolean leftWall, boolean bottomWall) {
        super(row, column, leftWall, bottomWall);
    }
}
