package edu.project4.rend.wikiImpl;

import edu.project4.Function;
import edu.project4.structures.FractalImage;
import edu.project4.structures.Pixel;
import edu.project4.structures.Point;
import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.concurrent.ThreadLocalRandom;

public class WikiRendererSingleThreaded implements WikiRenderer {

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

        double randomX;
        double randomY;

        RandomCollection<Function> randomCollection = new RandomCollection<>();
        for (Function function : transformations) {
            randomCollection.add(function.getProbability(), function);
        }

        for (int i = 0; i < numberSamples; i++) {
            randomX = random(xMIN, xMAX);
            randomY = random(yMIN, yMAX);
            double finalRandomX = randomX;
            double finalRandomY = randomY;
            dod(
                pixels,
                () -> {
                    Function next = randomCollection.next();
                    return new RR(next.calcFunction(new Point(finalRandomX, finalRandomY)), next.getAffineSpace());
                },
                xRes,
                yRes,
                randomX,
                randomY,
                iterationPerSample,
                symmetry,
                xMIN,
                xMAX,
                yMIN,
                yMAX
            );
        }

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
