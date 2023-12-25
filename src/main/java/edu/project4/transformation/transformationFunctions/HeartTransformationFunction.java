package edu.project4.transformation.transformationFunctions;

import edu.project4.structures.Point;

public class HeartTransformationFunction implements TransformationFunction {

    @Override public Point apply(Point point) {
        double x = point.x();
        double y = point.y();
        double hypotenuse = Math.sqrt(x * x + y * y);
        double expression = hypotenuse * Math.atan(y / x);
        return new Point(
            hypotenuse * Math.sin(expression),
            -hypotenuse * Math.cos(expression)
        );

    }

}
