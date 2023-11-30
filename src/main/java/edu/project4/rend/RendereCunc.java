package edu.project4.rend;

import edu.project4.Function;
import edu.project4.structures.FractalImage;
import edu.project4.structures.Pixel;
import edu.project4.structures.Point;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class RendereCunc extends Render {

    private final int countOfThreads;

    public RendereCunc(int countOfThreads) {this.countOfThreads = countOfThreads;}

    @Override
    public void rend(
        FractalImage fractalImage,

        List<Function> transformations,
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

            RandomCollection<Function> randomCollection = new RandomCollection<>();
            for (Function function : transformations) {
                randomCollection.add(function.getProbability(), function);
            }

            for (int i = 0; i < numberSamples; i++) {
                double randomX = random(xMIN, xMAX);
                double randomY = random(yMIN, yMAX);

                executorServices.submit(() -> {
                    dod(
                        pixels,
                        randomCollection,
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
                    );
                });

            }
        }

    }

    private void dod(

        Pixel[][] pixels,
        RandomCollection<Function> randomCollection,
        int xRes, int yRes,
        double randomX, double randomY,
        int iterationPerSample,
        int symmetry,
        double xMIN,
        double xMAX,
        double yMIN,
        double yMAX
    ) {
        double newX;
        double newY;
        double theta;
        double xRot, yRot;
        int x, y;

        for (int step = -20; step < iterationPerSample; step++) {

            Function transformation = randomCollection.next();

            newX = transformation.calcFunction(new Point(randomX, randomY)).x();
            newY = transformation.calcFunction(new Point(randomX, randomY)).y();

            if (step > 0) {
                theta = 0;
                for (int s = 0; s < symmetry; theta += 2 * Math.PI / symmetry, s++) {
                    xRot = newX * Math.cos(theta) - newY * Math.sin(theta);
                    yRot = newX * Math.sin(theta) + newY * Math.cos(theta);
                    if (xRot >= xMIN && xRot <= xMAX && yRot >= yMIN && yRot <= yMAX) {
                        x = xRes - (int) (((xMAX - xRot) / (xMAX - xMIN)) * xRes);
                        y = yRes - (int) (((yMAX - yRot) / (yMAX - yMIN)) * yRes);

                        if (x >= 0 && x < xRes && y >= 0 && y < yRes) {
                            synchronized (pixels[x][y]) {
                                changeColor(pixels[x][y], transformation.getAffineSpace());
                            }
                        }
                    }
                }
            }
        }
    }

}
