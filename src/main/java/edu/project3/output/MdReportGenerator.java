package edu.project3.output;

import edu.project3.metrics.Metric;
import java.util.ArrayList;

public class MdReportGenerator implements ReportGenerator {

    private final Printer printer;

    public MdReportGenerator(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void print(Metric metric) {
        printHeadline(metric.getHeadline());
        printTableHeadline(metric.getMetricData()
                                 .get(0));
        for (int i = 1; i < metric.getMetricData()
                                  .size(); i++) {
            printRow(metric.getMetricData()
                           .get(i));
            printer.print("\n");
        }
        printer.print("\n");

    }

    private void printHeadline(String headline) {
        printer.print("##### " + headline + "\n\n");
    }

    private void printRow(ArrayList<String> row) {
        for (String column : row) {
            printer.print("| " + column + "  ");
        }
        printer.print("|");
    }

    private void printTableHeadline(ArrayList<String> row) {
        for (String column : row) {
            printer.print("| " + column + "  ");
        }
        printer.print("|");
        printer.print("\n");
        for (String column : row) {
            printer.print("| --  ");
        }
        printer.print("|");
        printer.print("\n");
    }
}
