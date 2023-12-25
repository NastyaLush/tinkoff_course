package edu.project2.gameObjects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AStarSolverCell extends DepthCell {

    private Long weight = Long.MAX_VALUE;
    private AStarSolverCell parent;

    public AStarSolverCell(Cell cell) {
        super(cell);
    }
}
