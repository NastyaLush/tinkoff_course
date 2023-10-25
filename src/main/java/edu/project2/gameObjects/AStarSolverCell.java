package edu.project2.gameObjects;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class AStarSolverCell {

    private final Cell cell;
    private boolean visited;
    private AStarSolverCell parent;
    private Long weight = Long.MAX_VALUE;

}
