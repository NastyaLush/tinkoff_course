package edu.project3.metricsTest;

import edu.project3.argumentWork.ArgumentsManager;
import edu.project3.argumentWork.Util;
import edu.project3.metrics.MetricManager;
import edu.project3.metrics.MetricPublisher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
public class MetricManagerTest {

    @Mock
    ArgumentsManager argumentsManager;
    @Mock
    MetricPublisher metricPublisher;
    @InjectMocks
    private MetricManager metricManager;

    //-Dnet.bytebuddy.experimental=true
    @Test
    public void calcMetric_shouldFilterLogRecordAndCalcMetricForAllMetrics() {
        Mockito.when(argumentsManager.getArgumentFrom())
               .thenReturn(LocalDateTime.of(2010, 1, 1, 1, 1, 1, 1));
        Mockito.when(argumentsManager.getArgumentTo())
               .thenReturn(LocalDateTime.of(2025, 1, 1, 1, 1, 1, 1));

        try (MockedStatic<Util> util = Mockito.mockStatic(Util.class)) {
            util.when(() -> Util.getStream(Mockito.any()))
                .thenReturn(Stream.of(
                        "93.180.71.3 - - [17/May/2015:08:05:32 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\"",
                        "93.180.71.3 - - [17/May/2017:08:05:23 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\"",
                        "93.180.71.3 - - [17/May/2000:08:05:23 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\"",
                        "93.180.71.3 - - [17/May/2022:08:05:23 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\"",
                        "93.180.71.3 - - [17/May/2030:08:05:23 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\""));

            metricManager.calcMetric();
            Mockito.verify(metricPublisher, Mockito.times(3))
                   .updateMetrics(Mockito.any());
        }

    }

}
