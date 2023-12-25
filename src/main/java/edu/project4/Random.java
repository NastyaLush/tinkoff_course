package edu.project4;

import edu.project4.structures.Point;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.concurrent.ThreadLocalRandom;

public class Random {

    private Random() {
    }

    public static int randomInt(double from, double till) {
        return (int) (Math.random() * (till - from) + from);
    }

    public static Point randomPoint(double fromX, double tillX, double fromY, double tillY) {
        return new Point(randomDouble(fromX, tillX), randomDouble(fromY, tillY));
    }

    public static double randomDouble(double from, double till) {
        return (Math.random() * (till - from) + from);
    }

    public static class RandomCollection<E> {

        protected final NavigableMap<Double, E> map = new TreeMap<Double, E>();
        protected double total = 0;

        public void add(double weight, E result) {
            if (weight <= 0) {
                return;
            }
            total += weight;
            map.put(total, result);
        }

        public E next() {
            double value = ThreadLocalRandom.current().nextDouble() * total;
            return map.higherEntry(value).getValue();
        }
    }

}
