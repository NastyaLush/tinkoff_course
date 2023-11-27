package edu.project3.outputTest;

import edu.project3.metrics.MetricCommon;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrintFormatMarkdownTest {

    @Test
    @DisplayName("should print metrics in md format")
    public void print_shouldPrintMetricsInMDFormat() {
        String expectedAnswer = "##### Общая информация\n"
            + "\n"
            + "| Метрика  | Значение  |\n"
            + "| --  | --  |\n"
            + "| Начальная дата  | -  |\n"
            + "| Конечная дата  | -  |\n"
            + "| Количество запросов  | 0  |\n"
            + "| Средний размер ответа  | 0  |\n"
            + "\n";
        List<String> output = new ArrayList<>();
        MetricCommon metricCommon = new MetricCommon();

        edu.project3.output.MdReportGenerator
            printerWithFormatMarkdown = new edu.project3.output.MdReportGenerator(output::add);
        printerWithFormatMarkdown.generate(metricCommon);
        String actualAnswer = String.join("", output);

        assertEquals(expectedAnswer, actualAnswer);
    }

}
