package edu.project3.output;

import edu.project3.metrics.Metric;
import java.util.List;

public class PrintManager {

    public void print(OutputType outputType, Printer printer, List<Metric> metrics) {
        PrinterWithFormat printerF = getPrinter(outputType, printer);
        for (Metric metric : metrics) {
            assert printerF != null;
            printerF.print(metric);
        }
    }

    private PrinterWithFormat getPrinter(OutputType outputType, Printer printer) {
        switch (outputType) {
            case MARKDOWN -> {
                return new PrinterWithFormatMarkdown(printer);
            }
            case ADOC -> {
                return new PrinterWithFormatDoc(printer);
            }
            default -> {
                return null;
            }
        }
    }

}
