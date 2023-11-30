package edu.project4.postProcess;

import edu.project4.structures.FractalImage;
import edu.project4.structures.Pixel;

public class Reduce implements ImageProcessor {

    @Override
    public FractalImage process(FractalImage image) {
        int sample = 2;
        int h = image.height() / sample;
        int w = image.width() / sample;
        Pixel[][] newPixels = new Pixel[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                newPixels[i][j] = new Pixel();
            }
        }
        int R, G, B, count;
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                R = 0;
                G = 0;
                B = 0;
                count = 0;
                for (int sy = 0; sy < sample; sy++) {
                    for (int sx = 0; sx < sample; sx++) {
                        R += image.pixel(y * sample + sy, x * sample + sx).getR();
                        G += image.pixel(y * sample + sy, x * sample + sx).getG();
                        B += image.pixel(y * sample + sy, x * sample + sx).getB();

                        count += image.pixel(y * sample + sy, x * sample + sx).getHitCount();
                    }
                }
                newPixels[y][x].setR(R / (sample * sample));
                newPixels[y][x].setG(G / (sample * sample));
                newPixels[y][x].setB(B / (sample * sample));
                newPixels[y][x].setHitCount(count);
            }
        }
        return new FractalImage(newPixels, w, h);
    }
}
