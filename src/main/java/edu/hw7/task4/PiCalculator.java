package edu.hw7.task4;

import java.util.HashMap;
import java.util.Map;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;

@Log4j2
public class PiCalculator {

    private final double constForCalculatingPi = 4;

    public Double calcPI(Integer countOfIterations, Integer countOfThreads)
        throws InterruptedException {
        long start = System.nanoTime();

        Map<Thread, RunnableWithValue> threads = getThreadsWithResults(
            countOfIterations, countOfThreads);
        int circleCount = 0;
        int totalCount = 0;
        for (Map.Entry<Thread, RunnableWithValue> thread : threads.entrySet()) {
            thread.getKey().join();
            circleCount += thread.getValue().circleCount;
            totalCount += thread.getValue().totalCount;
        }
        Double answer = constForCalculatingPi * ((double) circleCount / totalCount);
        log.info(countOfThreads + " threads with " + countOfIterations + " executed in " + (System.nanoTime() - start)
                     + " nanosec with result " + answer);
        return answer;
    }

    @NotNull private Map<Thread, RunnableWithValue> getThreadsWithResults(
        Integer countOfIterations,
        Integer countOfThreads
    ) {
        Map<Thread, RunnableWithValue> threads = new HashMap<>();
        int countOfIterationsToOneThread = countOfIterations / countOfThreads;
        for (int i = 0; i < countOfThreads; i++) {
            if (i == countOfThreads - 1) {
                countOfIterationsToOneThread = countOfIterationsToOneThread + countOfIterations % countOfThreads;
            }
            RunnableWithValue runnable = new RunnableWithValue(countOfIterationsToOneThread);
            Thread thread = new Thread(runnable);
            threads.put(thread, runnable);
            thread.start();
        }
        return threads;
    }

    private class RunnableWithValue implements Runnable {

        private int totalCount = 0;
        private int circleCount = 0;
        private final Integer countOfIterations;

        private RunnableWithValue(Integer countOfIterations) {
            this.countOfIterations = countOfIterations;
        }

        @Override
        public void run() {
            for (int i = 0; i < countOfIterations; i++) {
                totalCount++;
                double pointX = generateRandom();
                double pointY = generateRandom();
                if (isInCircle(pointX, pointY)) {
                    circleCount++;
                }
            }
        }

        private boolean isInCircle(double x, double y) {
            return Math.pow(x, 2) + Math.pow(y, 2) <= 1;
        }

        private double generateRandom() {
            return Math.random() * ((double) 1 - (double) -1) + (double) -1;
        }
    }

}
