package edu.project4.rend;

import edu.project4.AffineSpace;
import edu.project4.structures.FractalImage;
import edu.project4.structures.Pixel;
import edu.project4.structures.Point;
import lombok.Getter;

public interface Renderer {

    default void pickPointAndTransformIt(

        FractalImage fractalImage,
        TransformationService transformationService,
        int symmetry,
        int iterationPerSample,
        ConfigRender configRender
    ) {

        for (int step = -20; step < iterationPerSample; step++) {
            ResultForMakingSymmetryAndChangeColor resultForMakingSymmetryAndChangeColor
                = transformationService.chosePoint();
            Point point = resultForMakingSymmetryAndChangeColor.getPoint();

            symmetryTransformations(
                fractalImage,
                resultForMakingSymmetryAndChangeColor.getAffineSpace(),
                point,
                step,
                symmetry,
                configRender
            );
        }
    }

    private void symmetryTransformations(
        FractalImage fractalImage,
        AffineSpace affineSpace,
        Point point,
        int step,
        int symmetry,
        ConfigRender configRender
    ) {

        double theta;
        if (step > 0) {
            theta = 0;
            for (int s = 0; s < symmetry; theta += 2 * Math.PI / symmetry, s++) {
                Point rotPoint = getSymmetryPoint(point, theta);
                if (rotPoint.x() >= configRender.xMIN() && rotPoint.x() <= configRender.xMAX()
                    && rotPoint.y() >= configRender.yMIN() && rotPoint.y() <= configRender.yMAX()) {
                    Point resultPoint = getResultPoint(
                        rotPoint,
                        fractalImage.height(),
                        fractalImage.width(),
                        configRender.xMIN(),
                        configRender.xMAX(),
                        configRender.yMIN(),
                        configRender.yMAX()
                    );
                    if (fractalImage.isInImage(resultPoint)) {
                        synchronized (fractalImage.pixel(resultPoint)) {
                            changeColor(fractalImage.pixel(resultPoint), affineSpace);
                        }
                    }
                }
            }
        }
    }

    private Point getSymmetryPoint(Point point, double theta) {
        return new Point(
            point.x() * Math.cos(theta) - point.y() * Math.sin(theta),
            point.x() * Math.sin(theta) + point.y() * Math.cos(theta)
        );
    }

    private Point getResultPoint(
        Point rotPoint, double xRes, double yRes, double xMIN, double xMAX, double yMIN, double yMAX
    ) {
        return new Point(
            xRes - (int) (((xMAX - rotPoint.x()) / (xMAX - xMIN)) * xRes),
            yRes - (int) (((yMAX - rotPoint.y()) / (yMAX - yMIN)) * yRes)
        );

    }

    default void changeColor(Pixel pixel, AffineSpace affineSpace) {

        pixel.setR((pixel.getR() + affineSpace.getRed()) / 2);
        pixel.setG((pixel.getG() + affineSpace.getGreen()) / 2);
        pixel.setB((pixel.getB() + affineSpace.getBlue()) / 2);

        pixel.incrementHitCount();
    }

    @Getter class ResultForMakingSymmetryAndChangeColor {

        Point point;
        AffineSpace affineSpace;

        public ResultForMakingSymmetryAndChangeColor(Point point, AffineSpace affineSpace) {
            this.point = point;
            this.affineSpace = affineSpace;
        }
    }
}
