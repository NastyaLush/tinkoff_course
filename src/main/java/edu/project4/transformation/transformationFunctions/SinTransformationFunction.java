package edu.project4.transformation.transformationFunctions;

import edu.project4.structures.Point;

public class SinTransformationFunction implements TransformationFunction {

    @Override
    public Point apply(Point point) {
        return new Point(Math.sin(point.x()), Math.sin(point.y()));
    }
}
