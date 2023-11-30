package edu.project4.postProcess;

import edu.project4.structures.FractalImage;

@FunctionalInterface
public
interface ImageProcessor {

    FractalImage process(FractalImage image);
}
