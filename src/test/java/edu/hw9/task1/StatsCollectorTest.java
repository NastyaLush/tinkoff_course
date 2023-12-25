package edu.hw9.task1;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StatsCollectorTest {

    @Test
    public void justTryToExecute() {
        String[] metricNames = new String[] {"m1", "m2", "m3", "m4", "m5"};
        StatsCollector statsCollector = new StatsCollector();
        try (ExecutorService executorService = Executors.newCachedThreadPool()) {
            for (int i = 0; i < 1000; i++) {
                executorService.submit(() -> {
                    for (int i1 = 0; i1 < 100; i1++) {
                        double[] stats = new double[ThreadLocalRandom.current().nextInt(1, 1000)];
                        for (int j = 0; j < stats.length; j++) {
                            stats[j] = ThreadLocalRandom.current().nextDouble();
                        }
                        statsCollector.push(
                            metricNames[ThreadLocalRandom.current()
                                                         .nextInt(0, metricNames.length)],
                            stats
                        );
                    }
                });
            }
        }
        for (Metric metric : statsCollector.stats()) {
            System.out.println(metric);
        }
    }

    @Test
    public void stats_shouldReturnCorrectStats() {
        String[] metricNames = new String[] {"m1", "m2", "m3", "m4", "m5"};
        StatsCollector statsCollector = new StatsCollector();
        double[] m1_1 = new double[] {1, 4, 3.5, 7, 2, 3};
        double[] m1_2 = new double[] {23332, 23, 5, 32, 2, 3, 4};
        double[] m2 = new double[] {1, 4, 3.5, 7, 2, 3};
        double[] m3 = new double[] {};
        double[] m4 = new double[] {1, -1};
        double[] m5 = new double[] {0};
        statsCollector.push("m1", m1_1);
        statsCollector.push("m1", m1_2);
        statsCollector.push("m2", m2);
        statsCollector.push("m3", m3);
        statsCollector.push("m4", m4);
        statsCollector.push("m5", m5);

        List<Metric> expected = List.of(
            new Metric(
                "m1",
                Stream.of(m1_1, m1_2).flatMapToDouble(Arrays::stream).sum(),
                Stream.of(m1_1, m1_2).flatMapToDouble(Arrays::stream).max().getAsDouble(),
                Stream.of(m1_1, m1_2).flatMapToDouble(Arrays::stream).min().getAsDouble(),
                Stream.of(m1_1, m1_2).flatMapToDouble(Arrays::stream).count()
            ),
            new Metric(
                "m2",
                Arrays.stream(m2).sum(),
                Arrays.stream(m2).max().getAsDouble(),
                Arrays.stream(m2).min().getAsDouble(),
                Arrays.stream(m2).count()
            ),
            new Metric("m4", 0, 1, -1, 2),
            new Metric("m5", 0, 0, 0, 1)
        );
        List<Metric> actual = statsCollector.stats();
        Assertions.assertArrayEquals(expected.toArray(), actual.toArray());
    }
}
