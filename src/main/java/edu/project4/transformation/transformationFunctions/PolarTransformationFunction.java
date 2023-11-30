package edu.project4.transformation.transformationFunctions;

import edu.project4.structures.Point;

public class PolarTransformationFunction implements TransformationFunction {

    @Override
    public Point apply(Point point) {
        double y = point.y();
        double x = point.x();
        return new Point(
            Math.atan(y / x) / Math.PI,
            Math.sqrt(x * x + y * y) - 1
        );
    }

}
