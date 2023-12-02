package edu.project4.rend.habrImpl;

import edu.project4.AffineSpace;
import edu.project4.rend.Renderer;
import edu.project4.structures.FractalImage;
import edu.project4.structures.Pixel;
import edu.project4.structures.Point;
import edu.project4.transformation.transformationFunctions.TransformationFunction;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class HabrRendererMultiThreaded implements HabrRenderer, Renderer {

    private final int countOfThreads;

    public HabrRendererMultiThreaded(int countOfThreads) {this.countOfThreads = countOfThreads;}

    public void rend(
        FractalImage fractalImage,
        List<AffineSpace> affineSpaces,
        List<TransformationFunction> transformationFunctions,
        int numberSamples,
        int iterationPerSample,
        int symmetry,
        double xMIN,
        double xMAX,
        double yMIN,
        double yMAX
    ) {
        Pixel[][] pixels = fractalImage.data();
        int xRes = fractalImage.height();
        int yRes = fractalImage.width();

        try (ExecutorService executorServices = Executors.newFixedThreadPool(countOfThreads)) {

            for (int i = 0; i < numberSamples; i++) {
                double randomX = random(xMIN, xMAX);
                double randomY = random(yMIN, yMAX);

                executorServices.submit(() -> dod(
                    pixels,
                    () -> {
                        AffineSpace affineSpace = affineSpaces.get((int) random(0, affineSpaces.size()));
                        Point point = affineSpace.apply(new Point(randomX, randomY));

                        TransformationFunction transformationFunction = transformationFunctions.get((int) random(
                            0,
                            transformationFunctions.size()
                        ));
                        return new RR(transformationFunction.apply(point), affineSpace);
                    },
                    xRes,
                    yRes,
                    randomX,
                    randomY,
                    iterationPerSample,
                    symmetry,
                    xMIN,
                    xMAX,
                    yMIN,
                    yMAX
                ));

            }
        }

    }

//    private void dod(
//        ChosePoint chosePoint,
//        int iterationPerSample,
//        int symmetry,
//        double xMIN,
//        double xMAX,
//        double yMIN,
//        double yMAX,
//        int xRes,
//        int yRes,
//        Pixel[][] pixels
//    ) {
//        double newX;
//        double newY;
//        for (int step = -20; step < iterationPerSample; step++) {
//            RR rr = chosePoint.chosePoint();
//            Point point = rr.getPoint();
//
//            newX = point.x();
//            newY = point.y();
//
//            extracted(symmetry, xMIN, xMAX, yMIN, yMAX, step, newX, newY, xRes, yRes, pixels, rr.getAffineSpace());
//        }
//    }
//
//    private void extracted(
//        int symmetry,
//        double xMIN,
//        double xMAX,
//        double yMIN,
//        double yMAX,
//        int step,
//        double newX,
//        double newY,
//        int xRes,
//        int yRes,
//        Pixel[][] pixels,
//        AffineSpace affineSpace
//    ) {
//        int x;
//        double xRot;
//        int y;
//        double yRot;
//        double theta;
//        if (step > 0) {
//            theta = 0;
//            for (int s = 0; s < symmetry; theta += 2 * Math.PI / symmetry, s++) {
//                xRot = newX * Math.cos(theta) - newY * Math.sin(theta);
//                yRot = newX * Math.sin(theta) + newY * Math.cos(theta);
//                if (xRot >= xMIN && xRot <= xMAX && yRot >= yMIN && yRot <= yMAX) {
//                    x = xRes - (int) (((xMAX - xRot) / (xMAX - xMIN)) * xRes);
//                    y = yRes - (int) (((yMAX - yRot) / (yMAX - yMIN)) * yRes);
//
//                    if (x >= 0 && x < xRes && y >= 0 && y < yRes) {
//                        synchronized (pixels[x][y]) {
//                            changeColor(pixels[x][y], affineSpace);
//                        }
//                    }
//                }
//            }
//        }
//    }

}
