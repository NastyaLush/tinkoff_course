package edu.project3.outputTest;

import edu.project3.metrics.MetricCommon;
import edu.project3.output.PrinterWithFormatDoc;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class PrintFormatDocTest {

    @Test
    @DisplayName("should print metrics in doc format")
    public void print_shouldPrintMetricsInDocFormat() {
        String expectedAnswer = "===== Общая информация\n"
                + "[cols=2]\n"
                + "|====\n"
                + "|Метрика\n"
                + "|Значение\n"
                + "\n"
                + "|Начальная дата\n"
                + "|-\n"
                + "\n"
                + "|Конечная дата\n"
                + "|-\n"
                + "\n"
                + "|Количество запросов\n"
                + "|0\n"
                + "\n"
                + "|Средний размер ответа\n"
                + "|0\n"
                + "\n"
                + "|====\n"
                + "\n";
        List<String> output = new ArrayList<>();
        MetricCommon metricCommon = new MetricCommon();

        PrinterWithFormatDoc printerWithFormatDoc = new PrinterWithFormatDoc(output::add);
        printerWithFormatDoc.print(metricCommon);
        String actualAnswer = String.join("", output);

        assertEquals(expectedAnswer, actualAnswer);
    }

}
