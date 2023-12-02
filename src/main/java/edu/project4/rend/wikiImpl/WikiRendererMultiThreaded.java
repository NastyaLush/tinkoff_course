package edu.project4.rend.wikiImpl;

import edu.project4.Function;
import edu.project4.structures.FractalImage;
import edu.project4.structures.Pixel;
import edu.project4.structures.Point;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class WikiRendererMultiThreaded extends WikiRendererSingleThreaded {

    private final int countOfThreads;

    public WikiRendererMultiThreaded(int countOfThreads) {this.countOfThreads = countOfThreads;}

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
                        () -> {
                            Function next = randomCollection.next();
                            return new RR(next.calcFunction(new Point(randomX, randomY)), next.getAffineSpace());
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
                    );
                });

            }
        }

    }

}
