package edu.hw7.task4;

import java.util.HashMap;
import java.util.Map;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class PiCalculator {

    public Double calcPI(Integer countOfIterations, Integer radius, Integer countOfThreads)
        throws InterruptedException {
        long start = System.nanoTime();

        HashMap<Thread, RunnableWithValue> threads = new HashMap<>();
        Integer countOfIterationsToOneThread = countOfIterations / countOfThreads;
        for (int i = 0; i < countOfThreads - 1; i++) {
            RunnableWithValue runnable = new RunnableWithValue(countOfIterationsToOneThread, radius);
            Thread thread = new Thread(runnable);
            threads.put(thread, runnable);
            thread.start();
        }
        RunnableWithValue runnable =
            new RunnableWithValue(countOfIterationsToOneThread + countOfIterations % countOfThreads, radius);
        Thread lastThread = new Thread(runnable);
        threads.put(lastThread, runnable);
        lastThread.start();
        int circleCount = 0;
        int totalCount = 0;
        for (Map.Entry<Thread, RunnableWithValue> thread : threads.entrySet()) {
            thread.getKey().join();
            circleCount += thread.getValue().circleCount;
            totalCount += thread.getValue().totalCount;
        }
        Double answer = 4d * ((double) circleCount / totalCount);
        log.info(countOfThreads + " threads with " + countOfIterations + " executed in " + (System.nanoTime() - start) +
            " nanosec with result " + answer);
        return answer;
    }

    private class RunnableWithValue implements Runnable {
        private int totalCount = 0;
        private int circleCount = 0;
        private final Integer countOfIterations;
        private final Integer radius;

        private RunnableWithValue(Integer countOfIterations, Integer radius) {
            this.countOfIterations = countOfIterations;
            this.radius = radius;
        }

        @Override
        public void run() {
            for (int i = 0; i < countOfIterations; i++) {
                totalCount++;
                double pointX = generateRandom(-radius, radius);
                double pointY = generateRandom(-radius, radius);
                if (isInCircle(pointX, pointY, radius)) {
                    circleCount++;
                }
            }
        }

        private boolean isInCircle(double x, double y, double radius) {
            return Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(radius, 2);
        }

        private double generateRandom(double from, double till) {
            return Math.random() * (till - from) + from;
        }
    }

}
