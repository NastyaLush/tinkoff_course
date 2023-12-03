package edu.project4.postProcess;

import edu.project4.structures.FractalImage;
import edu.project4.structures.Pixel;

public class Correction implements ImageProcessor {

    @Override
    public FractalImage process(FractalImage image, Double gamma) {
        double maxNormal = -1;
        for (int row = 0; row < image.width(); row++) {
            for (int col = 0; col < image.height(); col++) {
                Pixel pixel = image.pixel(row, col);
                if (pixel.getHitCount() != 0) {
                    pixel.setNormal(Math.log10(pixel.getHitCount()));
                }
                if (maxNormal < pixel.getNormal()) {
                    maxNormal = pixel.getNormal();
                }
            }
        }
        for (int row = 0; row < image.width(); row++) {
            for (int col = 0; col < image.height(); col++) {
                Pixel pixel = image.pixel(row, col);
                pixel.setNormal(pixel.getNormal() / maxNormal);
                pixel.setR((int) (pixel.getR() * Math.pow(pixel.getNormal(), 1d / gamma)));
                pixel.setG((int) (pixel.getG() * Math.pow(pixel.getNormal(), 1d / gamma)));
                pixel.setB((int) (pixel.getB() * Math.pow(pixel.getNormal(), 1d / gamma)));
            }
        }
        return image;
    }
}
