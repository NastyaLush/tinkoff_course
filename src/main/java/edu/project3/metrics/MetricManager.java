package edu.project3.metrics;

import edu.project3.argumentWork.ArgumentsManager;
import edu.project3.argumentWork.LogReaderService;
import edu.project3.structures.LogRecord;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.log4j.Log4j2;

@Log4j2 public class MetricManager {

    private final ArgumentsManager arguments;
    private final ArrayList<Metric> metrics = new ArrayList<>();

    public MetricManager(ArgumentsManager arguments) {
        arguments.validate();
        this.arguments = arguments;
    }

    public List<Metric> calcMetric() {
        try {
            LogReaderService.getStream(arguments.getPath()).map(LogRecord::parse).filter((this::filter))
                            .forEach(this::updateMetrics);
        } catch (IOException | InterruptedException e) {
            log.error("exception while iterate " + e.getMessage());
        }
        return metrics;
    }

    private boolean filter(LogRecord logRecord) {
        boolean answer = true;
        if (arguments.getDateFrom() != null) {

            answer = arguments.getDateFrom().isBefore(logRecord.dateOfRequest().toLocalDateTime());
        }
        if (arguments.getDateTo() != null) {
            answer = answer && arguments.getDateTo().isAfter(logRecord.dateOfRequest().toLocalDateTime());
        }
        return answer;
    }

    private void updateMetrics(LogRecord logRecord) {
        for (Metric metric : metrics) {
            metric.update(logRecord);
        }
    }

    public void addMetric(Metric metric) {
        metrics.add(metric);
    }

    public void addMetrics(List<Metric> metric) {
        metrics.addAll(metric);
    }
}
