package edu.project2.gameObjects;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class DepthCell {

    protected final Cell cell;
    protected boolean visited;

}
