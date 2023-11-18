package edu.project3;

import edu.project3.argument.ArgumentsManager;
import edu.project3.metrics.MetricCommon;
import edu.project3.metrics.MetricManager;
import edu.project3.metrics.MetricPopularity;
import edu.project3.metrics.MetricPublisher;
import edu.project3.output.FilePrinter;
import edu.project3.structures.LogRecord;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Main {

    private static final String COUNT_HEADLINE = "Количество";

    private Main() {
    }

    private static void workWithoutCommandArgs() throws IOException {
        String string = "logs/**/log.txt";
        ArgumentsManager argumentsManager = new ArgumentsManager();
        argumentsManager.setPathArgument(string);
        argumentsManager.setFormat("adoc");
        MetricPublisher metricPublisher = getMetricPublisher();

        new MetricManager(argumentsManager, metricPublisher, new FilePrinter(argumentsManager.getFormat()))
                .calcMetric();

    }

    private static MetricPublisher getMetricPublisher() {
        MetricPublisher metricPublisher = new MetricPublisher();
        metricPublisher.setMetric(new MetricCommon());
        metricPublisher.setMetric(new MetricPopularity("Самый Популярный IP",
                "IP", COUNT_HEADLINE, LogRecord::ipClient));
        metricPublisher.setMetric(new MetricPopularity("Запрашиваемые ресурсы",
                "Ресурс", COUNT_HEADLINE, logRecord -> {
            String[] pathToResource = logRecord.url()
                                               .resource()
                                               .split("/");
            String resource = pathToResource[pathToResource.length - 1];
            return resource;
        }));
        metricPublisher.setMetric(new MetricPopularity("Коды ответа",
                "Код", COUNT_HEADLINE, LogRecord::httpCodeStatus));
        return metricPublisher;
    }

    public static void main(String[] args) throws IOException {
        work(args);
    }

    private static void work(String[] args) throws IOException {
        if (args.length % 2 != 0) {
            log.error("the count of arguments must be even");
            return;
        }
        MetricPublisher metricPublisher = getMetricPublisher();

        ArgumentsManager arguments = new ArgumentsManager();
        try {
            for (int i = 0; i < args.length; i += 2) {
                switch (args[i]) {
                    case "--path" -> arguments.setPathArgument(args[i + 1]);
                    case "--from" -> arguments.setFrom(args[i + 1]);
                    case "--to" -> arguments.setTo(args[i + 1]);
                    case "--format" -> arguments.setFormat(args[i + 1]);
                    default -> {
                        log.error("there is this option");
                        throw new IllegalArgumentException();
                    }
                }
            }
            new MetricManager(arguments, metricPublisher, new FilePrinter(arguments.getFormat())).calcMetric();
        } catch (DateTimeParseException e) {
            log.error("the --from and --to should have 2011-12-03T10:15:30 format");
        }
    }

}
