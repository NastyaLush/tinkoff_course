package edu.project3.outputTest;

import edu.project3.metrics.MetricCommon;
import edu.project3.output.OutputType;
import edu.project3.output.ReportService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReportServiceTest {

    @Test
    @DisplayName("check if argument adoc so the print in adoc format")
    public void print_shouldPrintInCorrectIsStyleADOC() {
        ReportService reportService = new ReportService();
        MetricCommon metricCommon = new MetricCommon();
        List<String> expectedList = new ArrayList<>();
        new edu.project3.output.DocReportGenerator(expectedList::add).print(metricCommon);

        List<String> actualList = new ArrayList<>();
        reportService.report(OutputType.ADOC, actualList::add, List.of(metricCommon));

        assertEquals(expectedList, actualList);
    }

    @Test
    @DisplayName("check if argument md so the print in md format")
    public void print_shouldPrintInCorrectIsStyleMD() {
        ReportService reportService = new ReportService();
        MetricCommon metricCommon = new MetricCommon();
        List<String> expectedList = new ArrayList<>();
        new edu.project3.output.MdReportGenerator(expectedList::add).print(metricCommon);

        List<String> actualList = new ArrayList<>();
        reportService.report(OutputType.MARKDOWN, actualList::add, List.of(metricCommon));

        assertEquals(expectedList, actualList);
    }

    @Test
    @DisplayName("method works correctly with some metrics")
    public void print_shouldPrintAllMetrics() {
        ReportService reportService = new ReportService();
        MetricCommon metricCommon = new MetricCommon();
        MetricCommon metricCommon2 = new MetricCommon();
        List<String> expectedList = new ArrayList<>();
        new edu.project3.output.MdReportGenerator(expectedList::add).print(metricCommon);
        new edu.project3.output.MdReportGenerator(expectedList::add).print(metricCommon2);

        List<String> actualList = new ArrayList<>();
        reportService.report(OutputType.MARKDOWN, actualList::add, List.of(metricCommon, metricCommon2));

        assertEquals(expectedList, actualList);
    }

}
