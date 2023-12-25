package edu.project4.transformation.transformationFunctions;

import edu.project4.structures.Point;

public class HorseshoeTransformationFunction implements TransformationFunction {

    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();
        double r = Math.sqrt(x * x + y * y);
        return new Point(
            1 / r * (x - y) * (x + y),
            1 / r * 2 * x * y
        );
    }
}
