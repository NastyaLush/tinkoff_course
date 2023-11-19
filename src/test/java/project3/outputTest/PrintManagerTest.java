package project3.outputTest;

import edu.project3.metrics.MetricCommon;
import edu.project3.output.OutputType;
import edu.project3.output.PrintManager;
import edu.project3.output.PrinterWithFormatDoc;
import edu.project3.output.PrinterWithFormatMarkdown;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class PrintManagerTest {

    @Test
    @DisplayName("check if argument adoc so the print in adoc format")
    public void print_shouldPrintInCorrectIsStyleADOC() {
        PrintManager printManager = new PrintManager();
        MetricCommon metricCommon = new MetricCommon();
        List<String> expectedList = new ArrayList<>();
        new PrinterWithFormatDoc(expectedList::add).print(metricCommon);

        List<String> actualList = new ArrayList<>();
        printManager.print(OutputType.ADOC, actualList::add, List.of(metricCommon));

        assertEquals(expectedList, actualList);
    }

    @Test
    @DisplayName("check if argument md so the print in md format")
    public void print_shouldPrintInCorrectIsStyleMD() {
        PrintManager printManager = new PrintManager();
        MetricCommon metricCommon = new MetricCommon();
        List<String> expectedList = new ArrayList<>();
        new PrinterWithFormatMarkdown(expectedList::add).print(metricCommon);

        List<String> actualList = new ArrayList<>();
        printManager.print(OutputType.MARKDOWN, actualList::add, List.of(metricCommon));

        assertEquals(expectedList, actualList);
    }

    @Test
    @DisplayName("method works correctly with some metrics")
    public void print_shouldPrintAllMetrics() {
        PrintManager printManager = new PrintManager();
        MetricCommon metricCommon = new MetricCommon();
        MetricCommon metricCommon2 = new MetricCommon();
        List<String> expectedList = new ArrayList<>();
        new PrinterWithFormatMarkdown(expectedList::add).print(metricCommon);
        new PrinterWithFormatMarkdown(expectedList::add).print(metricCommon2);

        List<String> actualList = new ArrayList<>();
        printManager.print(OutputType.MARKDOWN, actualList::add, List.of(metricCommon, metricCommon2));

        assertEquals(expectedList, actualList);
    }


}
