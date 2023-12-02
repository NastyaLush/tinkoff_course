package edu.project4.rend.wikiImpl;

import edu.project4.Function;
import edu.project4.Random;
import edu.project4.rend.ConfigRender;
import edu.project4.structures.FractalImage;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.extern.log4j.Log4j2;
import static edu.project4.Random.randomPoint;

@Log4j2
public class WikiRendererMultiThreaded extends WikiRendererSingleThreaded {

    private final int countOfThreads;

    public WikiRendererMultiThreaded(int countOfThreads) {
        this.countOfThreads = countOfThreads;
    }

    @Override
    public void rend(
        FractalImage fractalImage,

        List<Function> transformations,
        int numberSamples,
        int iterationPerSample,
        int symmetry,
        ConfigRender configRender
    ) {
        try (ExecutorService executorServices = Executors.newFixedThreadPool(countOfThreads)) {

            Random.RandomCollection<Function> randomCollection = new Random.RandomCollection<>();
            for (Function function : transformations) {
                randomCollection.add(function.getProbability(), function);
            }

            for (int i = 0; i < numberSamples; i++) {

                executorServices.submit(() -> pickPointAndTransformIt(
                    fractalImage,
                    () -> {
                        Function next = randomCollection.next();
                        return new ResultForMakingSymmetryAndChangeColor(next.calcFunction(randomPoint(
                            configRender.xMIN(),
                            configRender.xMAX(),
                            configRender.yMIN(),
                            configRender.yMAX()
                        )), next.getAffineSpace());
                    },
                    symmetry, iterationPerSample,
                    configRender
                ));

            }
        }

    }

}
