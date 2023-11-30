package edu.project4.transformation.transformationFunctions;

import edu.project4.structures.Point;

public class DiskTransformationFunction implements TransformationFunction {

    @Override public Point apply(Point point) {
        double y = point.y();
        double x = point.x();
        double hypothesis = Math.PI * Math.sqrt(x * x + y * y);
        double expr = 1 / Math.PI * Math.atan(y / x);
        return new Point(
            expr * Math.sin(hypothesis),

            expr * Math.cos(hypothesis)
        );
    }

}
