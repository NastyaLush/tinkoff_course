package edu.project3.metrics;

import edu.project3.argument.ArgumentsManager;
import edu.project3.argument.Util;
import edu.project3.output.PrintManager;
import edu.project3.output.Printer;
import edu.project3.structures.LogRecord;
import java.io.IOException;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class MetricManager {

    private final ArgumentsManager arguments;
    private final MetricPublisher metricPublisher;
    private final Printer printer;

    public MetricManager(ArgumentsManager arguments, MetricPublisher metricPublisher, Printer printer) {
        this.printer = printer;
        arguments.validate();
        this.metricPublisher = metricPublisher;
        this.arguments = arguments;
    }

    public void calcMetric() {

        try {
            Util.getStream(arguments.getPathArgument())
                .map(LogRecord::parse)
                .filter((this::filter))
                .forEach(metricPublisher::updateMetrics);
            new PrintManager().print(arguments.getFormat(), printer, metricPublisher.getMetrics());
        } catch (IOException | InterruptedException e) {
            log.error("exception while iterate " + e.getMessage());
        }
    }

    public boolean filter(LogRecord logRecord) {
        boolean answer = true;
        if (arguments.getFrom() != null) {
            answer &= arguments.getFrom()
                               .isBefore(logRecord.dateOfRequest()
                                                  .toLocalDateTime());
        }
        if (arguments.getTo() != null) {
            answer &= arguments.getFrom()
                               .isAfter(logRecord.dateOfRequest()
                                                 .toLocalDateTime());
        }
        return answer;
    }
}
