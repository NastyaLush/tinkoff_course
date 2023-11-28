package edu.project3.output;

import edu.project3.metrics.Metric;
import java.util.List;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ReportService {

    public void report(OutputType outputType, Printer printer, List<Metric> metrics) {
        ReportGenerator reportGenerator = createGenerator(outputType, printer);
        for (Metric metric : metrics) {
            reportGenerator.print(metric);
        }
    }

    private ReportGenerator createGenerator(OutputType outputType, Printer printer) {
        switch (outputType) {
            case MARKDOWN -> {
                return new MdReportGenerator(printer);
            }
            case ADOC -> {
                return new DocReportGenerator(printer);
            }
            default -> {
                String message = "there is no option";
                log.error(message);
                throw new IllegalArgumentException(message);
            }
        }
    }

}
