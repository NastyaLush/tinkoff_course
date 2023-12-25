package edu.project4.structures;

public record FractalImage(Pixel[][] data, int height, int width) {

    public static FractalImage create(int height, int width) {
        Pixel[][] pixels = new Pixel[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                pixels[i][j] = new Pixel();
            }
        }
        return new FractalImage(pixels, height, width);
    }

    public Pixel pixel(int x, int y) {
        return data[x][y];
    }

    public Pixel pixel(Point point) {
        return data[(int) point.x()][(int) point.y()];
    }

    public boolean isInImage(Point point) {
        return point.x() >= 0 && point.x() < width && point.y() >= 0 && point.y() < height;
    }
}
