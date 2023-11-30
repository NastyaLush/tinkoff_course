package edu.project4;

import edu.project4.structures.Point;
import edu.project4.transformation.Transformation;
import edu.project4.transformation.transformationFunctions.TransformationFunction;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class Function {

    private final AffineSpace affineSpace;
    private final List<Transformation> transformationList;
    private final Double probability;

    public Function(AffineSpace affineSpace, List<Transformation> transformationList, double probability) {
        this.affineSpace = affineSpace;
        this.transformationList = transformationList;
        this.probability = probability;
    }

    public static Function generateFunction(List<TransformationFunction> transformationList) {
        return new Function(new AffineSpace(), generateTransformations(transformationList), Math.random());
    }

    private static List<Transformation> generateTransformations(List<TransformationFunction> transformationList) {
        int countOfTransformations = random(1, transformationList.size());
        double till = 1;
        List<Transformation> transformations = new ArrayList<>();
        while (countOfTransformations > 0) {
            int currentFunction = random(0, transformationList.size() - 1);
            double weight = randomD(0, till);
            transformations.add(new Transformation(transformationList.get(currentFunction), weight));
            transformationList.remove(currentFunction);
            till -= weight;
            countOfTransformations--;
        }
        return transformations;
    }

    private static int random(double from, double till) {
        return (int) (Math.random() * (till - from) + from);
    }

    private static double randomD(double from, double till) {
        return (Math.random() * (till - from) + from);
    }

    public static List<Function> generateFunctions(
        List<TransformationFunction> transformationList,
        Integer countOfFunctions
    ) {
        double till = 1;
        List<Function> functions = new ArrayList<>();
        for (int i = 0; i < countOfFunctions; i++) {
            double weight = randomD(0, till);
            functions.add(generateFunction(transformationList, weight));
            till -= weight;
        }
        return functions;
    }

    public static Function generateFunction(List<TransformationFunction> transformationList, double probability) {
        return new Function(new AffineSpace(), generateTransformations(transformationList), probability);
    }

    public Point calcFunction(Point point) {
        Point point1 = affineSpace.apply(point);
        return transform(point1);
    }

    private Point transform(Point point) {
        double sumX = 0;
        double sumY = 0;
        for (Transformation transformation : transformationList) {
            Point newPoint = transformation.transform(point);
            sumX += transformation.getWeight() * newPoint.x();
            sumY += transformation.getWeight() * newPoint.y();
        }
        return new Point(sumX, sumY);
    }
}
