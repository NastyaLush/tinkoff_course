package edu.project4.transformation.transformationFunctions;

import edu.project4.structures.Point;

public class SwirlTransformationFunction implements TransformationFunction {

    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();
        double r = Math.sqrt(x * x + y * y);
        return new Point(
            x * Math.sin(r * r) - y * Math.cos(r * r),
            x * Math.cos(r * r) + y * Math.sin(r * r)
        );
    }
}
