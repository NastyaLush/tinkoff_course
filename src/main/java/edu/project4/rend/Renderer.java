package edu.project4.rend;

import edu.project4.Function;
import edu.project4.structures.FractalImage;
import java.util.List;

@FunctionalInterface
public interface Renderer {

    void rend(
        FractalImage fractalImage,

        List<Function> transformations,
        int numberSamples,
        int iterationPerSample,
        int symmetry,
        double xMIN,
        double xMAX,
        double yMIN,
        double yMAX
    );

}
