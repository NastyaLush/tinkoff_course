package edu.project4.postProcess;

import edu.project4.structures.FractalImage;
import edu.project4.structures.Pixel;

public class Reduce {

    @SuppressWarnings("NestedForDepth")
    public FractalImage process(FractalImage image) {
        int sample = 2;
        int h = image.width() / sample;
        int w = image.height() / sample;
        Pixel[][] newPixels = new Pixel[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                newPixels[i][j] = new Pixel();
            }
        }
        int r;
        int g;
        int b;
        int count;
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                r = 0;
                g = 0;
                b = 0;
                count = 0;
                for (int sy = 0; sy < sample; sy++) {
                    for (int sx = 0; sx < sample; sx++) {
                        r += image.pixel(y * sample + sy, x * sample + sx).getR();
                        g += image.pixel(y * sample + sy, x * sample + sx).getG();
                        b += image.pixel(y * sample + sy, x * sample + sx).getB();

                        count += image.pixel(y * sample + sy, x * sample + sx).getHitCount();
                    }
                }
                newPixels[y][x].setR(r / (sample * sample));
                newPixels[y][x].setG(g / (sample * sample));
                newPixels[y][x].setB(b / (sample * sample));
                newPixels[y][x].setHitCount(count);
            }
        }
        return new FractalImage(newPixels, w, h);
    }
}
