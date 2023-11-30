package edu.project4.transformation;

import edu.project4.structures.Point;
import edu.project4.transformation.transformationFunctions.TransformationFunction;
import lombok.Getter;

public class Transformation {

    @Getter private final double weight;
    private final TransformationFunction transformationFunction;

    public Transformation(TransformationFunction transformationFunction) {
        this.transformationFunction = transformationFunction;
        this.weight = Math.random();
    }

    public Transformation(TransformationFunction transformationFunction, double weight) {
        this.weight = weight;
        this.transformationFunction = transformationFunction;
    }

    public Point transform(Point point) {
        Point newPoint = transformationFunction.apply(point);
        return new Point(newPoint.x() * weight, newPoint.y() * weight);
    }

}
