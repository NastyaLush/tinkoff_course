package edu.project3.metrics;

import edu.project3.structures.LogRecord;
import java.util.ArrayList;
import java.util.List;

public class MetricPublisher {

    private final List<Metric> metrics = new ArrayList<>();

    public void updateMetrics(LogRecord logRecord) {
        for (Metric metric : metrics) {
            metric.update(logRecord);
        }
    }

    public void setMetric(Metric metric) {
        metrics.add(metric);
    }

    public List<Metric> getMetrics() {
        return metrics;
    }

    public void removeMetric(Metric metric) {
        metrics.remove(metric);
    }
}
