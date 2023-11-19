package edu.project3.metrics;

import edu.project3.argumentWork.ArgumentsManager;
import edu.project3.argumentWork.Util;
import edu.project3.structures.LogRecord;
import java.io.IOException;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class MetricManager {

    private final ArgumentsManager arguments;
    private final MetricPublisher metricPublisher;


    public MetricManager(ArgumentsManager arguments, MetricPublisher metricPublisher) {
        arguments.validate();
        this.metricPublisher = metricPublisher;
        this.arguments = arguments;
    }

    public void calcMetric() {
        try {
            Util.getStream(arguments.getArgumentPath())
                .map(LogRecord::parse)
                .filter((this::filter))
                .forEach(metricPublisher::updateMetrics);
//            new PrintManager().print(arguments.getArgumentFormat(), printer, metricPublisher.getMetrics());
        } catch (IOException | InterruptedException e) {
            log.error("exception while iterate " + e.getMessage());
        }
    }

    private boolean filter(LogRecord logRecord) {
        boolean answer = true;
        if (arguments.getArgumentFrom() != null) {

            answer = arguments.getArgumentFrom()
                              .isBefore(logRecord.dateOfRequest()
                                                 .toLocalDateTime());
        }
        if (arguments.getArgumentTo() != null) {
            answer = answer && arguments.getArgumentTo()
                                        .isAfter(logRecord.dateOfRequest()
                                                          .toLocalDateTime());
        }
        return answer;
    }
}
