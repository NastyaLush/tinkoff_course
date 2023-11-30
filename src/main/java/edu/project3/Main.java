package edu.project3;

import edu.project3.argumentWork.ArgumentsManager;
import edu.project3.metrics.Metric;
import edu.project3.metrics.MetricCommon;
import edu.project3.metrics.MetricManager;
import edu.project3.metrics.MetricPopularity;
import edu.project3.output.ConsolePrinter;
import edu.project3.output.ReportService;
import edu.project3.structures.LogRecord;
import java.io.IOException;
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
        MetricManager metricManager = new MetricManager(argumentsManager);
        new ReportService().report(
            argumentsManager.getReportFormat(),
            new ConsolePrinter(),
            metricManager.calcMetric()
        );
    }

    private static void configureMetrics(MetricManager metricManager) {
        metricManager.addMetric(new MetricCommon());
        metricManager.addMetric(new MetricPopularity(
            "Самый Популярный IP",
            "IP",
            COUNT_HEADLINE,
            LogRecord::ipClient
        ));
        metricManager.addMetric(new MetricPopularity("Запрашиваемые ресурсы", "Ресурс", COUNT_HEADLINE, logRecord -> {
            String[] pathToResource = logRecord.url().resource().split("/");
            return pathToResource[pathToResource.length - 1];
        }));
        metricManager.addMetric(new MetricPopularity(
            "Коды ответа",
            "Код",
            COUNT_HEADLINE,
            LogRecord::httpCodeStatus
        ));
    }

    public static void main(String[] args) throws IOException {
        work(args);
    }

    private static void work(String[] args) throws IOException {

        ArgumentsManager argumentsManager = ArgumentsManager.parseArgs(args);
        MetricManager metricManager = new MetricManager(argumentsManager);
        configureMetrics(metricManager);
        List<Metric> metricList = metricManager.calcMetric();
        new ReportService().report(
            argumentsManager.getReportFormat(),
            new ConsolePrinter(),
            metricList
        );

    }

}
