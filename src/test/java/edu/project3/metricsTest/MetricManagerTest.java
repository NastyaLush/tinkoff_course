package edu.project3.metricsTest;

import edu.project3.argumentWork.ArgumentsManager;
import edu.project3.argumentWork.LogReaderService;
import edu.project3.metrics.MetricCommon;
import edu.project3.metrics.MetricManager;
import edu.project3.metrics.MetricPopularity;
import java.time.LocalDateTime;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class MetricManagerTest {

    @Test
    public void calcMetric_shouldFilterLogRecordAndCalcMetricForAllMetrics() {
        ArgumentsManager argumentsManager = Mockito.mock(ArgumentsManager.class);
        MetricCommon metricCommon = Mockito.mock(MetricCommon.class);
        MetricPopularity metricPopularity = Mockito.mock(MetricPopularity.class);

        MetricManager metricManager = new MetricManager(argumentsManager);
        metricManager.addMetric(metricCommon);
        metricManager.addMetric(metricPopularity);

        Mockito.when(argumentsManager.getDateFrom())
               .thenReturn(LocalDateTime.of(2010, 1, 1, 1, 1, 1, 1));
        Mockito.when(argumentsManager.getDateTo())
               .thenReturn(LocalDateTime.of(2025, 1, 1, 1, 1, 1, 1));

        try (
            MockedStatic<LogReaderService> util = Mockito.mockStatic(LogReaderService.class)) {
            util.when(() -> LogReaderService.getStream(Mockito.any()))
                .thenReturn(Stream.of(
                    "93.180.71.3 - - [17/May/2015:08:05:32 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\"",
                    "93.180.71.3 - - [17/May/2017:08:05:23 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\"",
                    "93.180.71.3 - - [17/May/2000:08:05:23 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\"",
                    "93.180.71.3 - - [17/May/2022:08:05:23 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\"",
                    "93.180.71.3 - - [17/May/2030:08:05:23 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\""
                ));

            metricManager.calcMetric();

            Mockito.verify(metricCommon, times(3))
                   .update(Mockito.any());
            Mockito.verify(metricPopularity, times(3))
                   .update(Mockito.any());
        }

    }

}
