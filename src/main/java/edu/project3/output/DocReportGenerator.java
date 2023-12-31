package edu.project3.output;

import edu.project3.metrics.Metric;
import java.util.ArrayList;

public class DocReportGenerator implements ReportGenerator {

    private final Printer printer;

    public DocReportGenerator(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void print(Metric metric) {
        printHeadline(metric.getHeadline());
        printColumns(metric.getMetricData()
                           .get(0)
                           .size());
        printer.print("|====\n");
        for (ArrayList<String> row : metric.getMetricData()) {
            printRow(row);
            printer.print("\n");
        }
        printer.print("|====");
        printer.print("\n\n");

    }

    private void printHeadline(String headline) {
        printer.print("===== " + headline + "\n");
    }

    private void printColumns(Integer countOfColumns) {
        printer.print("[cols=" + countOfColumns + "]" + "\n");
    }

    private void printRow(ArrayList<String> row) {
        for (String column : row) {
            printer.print("|" + column + "\n");
        }
    }
}
