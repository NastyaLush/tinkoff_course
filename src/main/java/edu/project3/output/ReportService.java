package edu.project3.output;

import edu.project3.metrics.Metric;
import java.util.List;

public class ReportService {

    public void report(OutputType outputType, Printer printer, List<Metric> metrics) {
        ReportGenerator printerF = getPrinter(outputType, printer);
        for (Metric metric : metrics) {
            assert printerF != null;
            printerF.generate(metric);
        }
    }

    private ReportGenerator getPrinter(OutputType outputType, Printer printer) {
        switch (outputType) {
            case MARKDOWN -> {
                return new MdReportGenerator(printer);
            }
            case ADOC -> {
                return new DocReportGenerator(printer);
            }
            default -> {
                return null;
            }
        }
    }

}
