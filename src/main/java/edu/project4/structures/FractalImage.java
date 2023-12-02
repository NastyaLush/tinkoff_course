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

    public Pixel pixel(Point point) {
        return data[(int) point.x()][(int) point.y()];
    }

    public boolean isInImage(Point point) {
        return point.x() >= 0 && point.x() < height && point.y() >= 0 && point.y() < width;
    }
}
