package edu.hw9.task1;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StatsCollector {

    private final Map<String, Metric> metricMap = new ConcurrentHashMap<>();

    private final ExecutorService executorService = Executors.newCachedThreadPool();

    public void push(String metricName, double[] data) {
        executorService.submit(new Runnable() {
            private double sum = 0;
            private long count = 0;
            private double max = -Double.MAX_VALUE;
            private double min = Double.MAX_VALUE;

            @Override
            public void run() {
                if (data.length == 0) {
                    return;
                }
//                if (!metricMap.containsKey(metricName)) {
//                    System.out.println(metricMap.get(metricName));
//                    System.out.println("create " + metricName);
//                    metricMap.put(metricName, new Metric(metricName));
//                }
                metricMap.putIfAbsent(metricName, new Metric(metricName));
                for (double stat : data) {
                    sum += stat;
                    count++;
                    if (stat > max) {
                        max = stat;
                    }
                    if (stat < min) {
                        min = stat;
                    }
                }
                metricMap.get(metricName).addSum(sum);
                metricMap.get(metricName).addMin(min);
                metricMap.get(metricName).addMax(max);
                metricMap.get(metricName).addCount(count);
            }

        });
    }

    public List<Metric> stats() {
        return metricMap.values().stream().toList();
    }

}
