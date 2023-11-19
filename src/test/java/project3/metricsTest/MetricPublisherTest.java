package project3.metricsTest;

import edu.project3.metrics.Metric;
import edu.project3.metrics.MetricCommon;
import edu.project3.metrics.MetricPopularity;
import edu.project3.metrics.MetricPublisher;
import edu.project3.structures.LogRecord;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import java.util.List;

public class MetricPublisherTest {

    @Test
    @DisplayName("method setMetric should set metric")
    public void setMetric_shouldSetMetric() {
        MetricPublisher metricPublisher = new MetricPublisher();
        List<Metric> expectedArray = List.of();
        List<Metric> actualArray = metricPublisher.getMetrics();

        assertEquals(expectedArray, actualArray);

        MetricCommon metricCommon = new MetricCommon();
        expectedArray = List.of(metricCommon);
        metricPublisher.setMetric(metricCommon);
        actualArray = metricPublisher.getMetrics();

        assertEquals(expectedArray, actualArray);
    }

    //works with flag -Dnet.bytebuddy.experimental=true
    @Test
    public void updateMetrics_shouldCalMethodUpdateFromMetrics() {
        MetricPublisher metricPublisher = new MetricPublisher();
        MetricCommon metricCommon = Mockito.mock(MetricCommon.class);
        MetricPopularity metricPopularity = Mockito.mock(MetricPopularity.class);
        LogRecord logRecord = Mockito.mock(LogRecord.class);
        metricPublisher.setMetric(metricCommon);
        metricPublisher.setMetric(metricPopularity);

        metricPublisher.updateMetrics(logRecord);

        Mockito.verify(metricCommon, times(1))
               .update(logRecord);
        Mockito.verify(metricPopularity, times(1))
               .update(logRecord);

    }

}
