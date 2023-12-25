package edu.project4.rend.habrImpl;

import edu.project4.AffineSpace;
import edu.project4.rend.ConfigRender;
import edu.project4.rend.Renderer;
import edu.project4.structures.FractalImage;
import edu.project4.transformation.transformationFunctions.TransformationFunction;
import java.util.List;

@FunctionalInterface
public interface HabrRenderer extends Renderer {

    void rend(
        FractalImage fractalImage,
        List<AffineSpace> affineSpaces,
        List<TransformationFunction> transformationFunctions,
        int numberSamples,
        int iterationPerSample,
        int symmetry,
        ConfigRender configRender
    );
}
