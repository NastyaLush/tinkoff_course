package edu.project4.rend.wikiImpl;

import edu.project4.Function;
import edu.project4.rend.ConfigRender;
import edu.project4.rend.Renderer;
import edu.project4.structures.FractalImage;
import java.util.List;

@FunctionalInterface
public interface WikiRenderer extends Renderer {

    void rend(
        FractalImage fractalImage,
        List<Function> transformations,
        int numberSamples,
        int iterationPerSample,
        int symmetry,
        ConfigRender configRender
    );
}
