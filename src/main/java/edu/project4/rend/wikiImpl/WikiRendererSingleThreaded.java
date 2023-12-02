package edu.project4.rend.wikiImpl;

import edu.project4.Function;
import edu.project4.Random;
import edu.project4.rend.ConfigRender;
import edu.project4.structures.FractalImage;
import java.util.List;
import static edu.project4.Random.randomPoint;

public class WikiRendererSingleThreaded implements WikiRenderer {

    @Override
    public void rend(
        FractalImage fractalImage,
        List<Function> transformations,
        int numberSamples,
        int iterationPerSample,
        int symmetry,
        ConfigRender configRender
    ) {

        Random.RandomCollection<Function> randomCollection = new Random.RandomCollection<>();
        for (Function function : transformations) {
            randomCollection.add(function.getProbability(), function);
        }

        for (int i = 0; i < numberSamples; i++) {

            pickPointAndTransformIt(
                fractalImage,
                () -> {
                    Function next = randomCollection.next();
                    return new ResultForMakingSymmetryAndChangeColor(next.calcFunction(
                        randomPoint(
                            configRender.xMIN(),
                            configRender.xMAX(),
                            configRender.yMIN(),
                            configRender.yMAX()
                        )), next.getAffineSpace());
                },
                symmetry, iterationPerSample,
                configRender
            );
        }

    }

}
