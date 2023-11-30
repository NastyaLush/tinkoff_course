package edu.project4.structures;

public record FractalImage(Pixel[][] data, int width, int height) {

    public static FractalImage create(int width, int height) {
        Pixel[][] pixels = new Pixel[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                pixels[i][j] = new Pixel();
            }
        }
        return new FractalImage(pixels, width, height);
    }

    public Pixel pixel(int x, int y) {
        return data[x][y];
    }
}
