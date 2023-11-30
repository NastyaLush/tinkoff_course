package edu.project4.rend;

import edu.project4.AffineSpace;
import edu.project4.Function;
import edu.project4.structures.FractalImage;
import edu.project4.structures.Pixel;
import edu.project4.structures.Point;
import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.concurrent.ThreadLocalRandom;

public class Render implements Renderer {

    @Override
    public void rend(
        FractalImage fractalImage,

        List<Function> transformations,
        int numberSamples,
        int iterationPerSample,
        int symmetry,
        double xMIN,
        double xMAX,
        double yMIN,
        double yMAX
    ) {
        Pixel[][] pixels = fractalImage.data();
        int xRes = fractalImage.height();
        int yRes = fractalImage.width();

        double newX;
        double newY;
        double randomX;
        double randomY;
        double theta;
        double xRot, yRot;
        int x, y;
        RandomCollection<Function> randomCollection = new RandomCollection<>();
        for (Function function : transformations) {
            randomCollection.add(function.getProbability(), function);
        }

        for (int i = 0; i < numberSamples; i++) {
            randomX = random(xMIN, xMAX);
            randomY = random(yMIN, yMAX);
            for (int step = -20; step < iterationPerSample; step++) {

                Function transformation = randomCollection.next();

                newX = transformation.calcFunction(new Point(randomX, randomY)).x();
                newY = transformation.calcFunction(new Point(randomX, randomY)).y();

                if (step > 0) {
                    theta = 0;

                    for (int s = 0; s < symmetry; theta += 2 * Math.PI / symmetry, s++) {

                        xRot = newX * Math.cos(theta) - newY * Math.sin(theta);
                        yRot = newX * Math.sin(theta) + newY * Math.cos(theta);

                        if (xRot >= xMIN && xRot <= xMAX && yRot >= yMIN && yRot <= yMAX) {
                            x = xRes - (int) (((xMAX - xRot) / (xMAX - xMIN)) * xRes);
                            y = yRes - (int) (((yMAX - yRot) / (yMAX - yMIN)) * yRes);

                            if (x >= 0 && x < xRes && y >= 0 && y < yRes) {
                                changeColor(pixels[x][y], transformation.getAffineSpace());
                            }
                        }
                    }

                }
            }
        }

    }

    protected double random(double from, double till) {
        return Math.random() * (till - from) + from;
    }

    protected void changeColor(Pixel pixel, AffineSpace affineSpace) {

        pixel.setR((pixel.getR() + affineSpace.getRed()) / 2);
        pixel.setG((pixel.getG() + affineSpace.getGreen()) / 2);
        pixel.setB((pixel.getB() + affineSpace.getBlue()) / 2);

        pixel.incrementHitCount();

    }

    protected static class RandomCollection<E> {

        protected final NavigableMap<Double, E> map = new TreeMap<Double, E>();
        protected double total = 0;

        protected void add(double weight, E result) {
            if (weight <= 0) {return;}
            total += weight;
            map.put(total, result);
        }

        protected E next() {
            double value = ThreadLocalRandom.current().nextDouble() * total;
            return map.higherEntry(value).getValue();
        }
    }

}
