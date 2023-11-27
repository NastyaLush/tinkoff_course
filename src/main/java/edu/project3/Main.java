package edu.project3;

import edu.project3.argumentWork.ArgumentsManager;
import edu.project3.metrics.Metric;
import edu.project3.metrics.MetricCommon;
import edu.project3.metrics.MetricManager;
import edu.project3.metrics.MetricPopularity;
import edu.project3.output.ReportService;
import edu.project3.structures.LogRecord;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.log4j.Log4j2;

@Log4j2 public class Main {

    private static final String COUNT_HEADLINE = "Количество";

    private Main() {
    }

    private static void workWithoutCommandArgs() throws IOException {
        String string = "logs/**/log.txt";
        ArgumentsManager argumentsManager = new ArgumentsManager();
        argumentsManager.setPath(string);
        argumentsManager.setReportFormat("adoc");
        new edu.project3.output.ConsolePrinter();
        new ReportService().report(
            argumentsManager.getReportFormat(),
            new edu.project3.output.ConsolePrinter(),
            new MetricManager(argumentsManager, getMetrics()).calcMetric()
        );
    }

    private static Metric[] getMetrics() {
        ArrayList<Metric> metrics = new ArrayList<>();
        metrics.add(new MetricCommon());
        metrics.add(new MetricPopularity(
            "Самый Популярный IP",
            "IP",
            COUNT_HEADLINE,
            LogRecord::ipClient
        ));
        metrics.add(new MetricPopularity("Запрашиваемые ресурсы", "Ресурс", COUNT_HEADLINE, logRecord -> {
            String[] pathToResource = logRecord.url().resource().split("/");
            String resource = pathToResource[pathToResource.length - 1];
            return resource;
        }));
        metrics.add(new MetricPopularity(
            "Коды ответа",
            "Код",
            COUNT_HEADLINE,
            LogRecord::httpCodeStatus
        ));
        return metrics.toArray(new Metric[] {});
    }

    public static void main(String[] args) throws IOException {
        work(args);
    }

    private static void work(String[] args) throws IOException {
        if (args.length % 2 != 0) {
            log.error("the count of arguments must be even");
            return;
        }

        ArgumentsManager arguments = new ArgumentsManager();

        for (int i = 0; i < args.length; i += 2) {
            switch (args[i]) {
                case "--path" -> arguments.setPath(args[i + 1]);
                case "--from" -> arguments.setDateFrom(args[i + 1]);
                case "--to" -> arguments.setDateTo(args[i + 1]);
                case "--format" -> arguments.setReportFormat(args[i + 1]);
                default -> {
                    log.error("there is this option");
                    throw new IllegalArgumentException();
                }
            }
        }
        List<Metric> metricList = new MetricManager(arguments, getMetrics()).calcMetric();
        new ReportService().report(
            arguments.getReportFormat(),
            new edu.project3.output.ConsolePrinter(),
            metricList
        );

    }

}
