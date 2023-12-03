package edu.project4.rend.habrImpl;

import edu.project4.AffineSpace;
import edu.project4.rend.ConfigRender;
import edu.project4.structures.FractalImage;
import edu.project4.structures.Point;
import edu.project4.transformation.transformationFunctions.TransformationFunction;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import static edu.project4.Random.randomInt;
import static edu.project4.Random.randomPoint;

@Log4j2
public class HabrRendererSingleThreaded implements HabrRenderer {

    private final int countOfThreads;

    public HabrRendererSingleThreaded(int countOfThreads) {
        this.countOfThreads = countOfThreads;
    }

    public void rend(
        FractalImage fractalImage,
        List<AffineSpace> affineSpaces,
        List<TransformationFunction> transformationFunctions,
        int numberSamples,
        int iterationPerSample,
        int symmetry,
        ConfigRender configRender
    ) {

        for (int i = 0; i < numberSamples; i++) {
            pickPointAndTransformIt(
                fractalImage,
                () -> {
                    AffineSpace affineSpace = affineSpaces.get(randomInt(0, affineSpaces.size()));
                    Point point = affineSpace.apply(randomPoint(
                        configRender.xMIN(),
                        configRender.xMAX(),
                        configRender.yMIN(),
                        configRender.yMAX()
                    ));
                    TransformationFunction transformationFunction = transformationFunctions.get(randomInt(
                        0,
                        transformationFunctions.size()
                    ));
                    return new ResultForMakingSymmetryAndChangeColor(
                        transformationFunction.apply(point),
                        affineSpace
                    );
                },
                symmetry, iterationPerSample,
                configRender

            );
        }
    }

}
