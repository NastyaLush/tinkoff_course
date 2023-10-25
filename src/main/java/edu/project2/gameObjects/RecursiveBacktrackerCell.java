package edu.project2.gameObjects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecursiveBacktrackerCell extends Cell {

    private boolean visited;

    public RecursiveBacktrackerCell(Integer row, Integer column, boolean leftWall, boolean bottomWall) {
        super(row, column, leftWall, bottomWall);
        this.visited = false;
    }

}
