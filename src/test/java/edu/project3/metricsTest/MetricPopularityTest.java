package edu.project3.metricsTest;

import edu.project3.metrics.MetricCommon;
import edu.project3.metrics.MetricPopularity;
import edu.project3.metrics.MetricPublisher;
import edu.project3.structures.LogRecord;
import edu.project3.structures.URL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.math.BigInteger;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class MetricPopularityTest {

    private static ArrayList<ArrayList<String>> getMetric(List<Long> statitics) {
        ArrayList<ArrayList<String>> metrics = new ArrayList<>();
        metrics.add(new ArrayList<>(List.of(new String[]{"Код", "Количество"})));
        for (int i = 0; i < statitics.size(); i += 2) {
            metrics.add(new ArrayList<>(List.of(new String[]{String.valueOf(statitics.get(i)), String.valueOf(statitics.get(
                    i + 1))})));
        }
        return metrics;
    }

    private static MetricPopularity getMetricPopularity(Stream<LogRecord> logRecordStream) {
        MetricPopularity metricPopularity = new MetricPopularity("Коды ответа",
                "Код", "Количество", LogRecord::httpCodeStatus);
        ;
        logRecordStream.forEach((metricPopularity::update));
        return metricPopularity;
    }

    public static Stream<Arguments> updateProvider() {
        return Stream.of(
                Arguments.of(getMetricPopularity(Stream.of(
                                new LogRecord("1", "1", OffsetDateTime.of(2000, 1, 1, 1, 1, 1, 1, ZoneOffset.UTC), new URL(
                                        "GET", "dddd", "dddd"
                                ), "400", 2L, "d", "d")
                        )),
                        getMetric(List.of(400L, 1L))
                ),
                Arguments.of(getMetricPopularity(Stream.of(
                                new LogRecord("1", "1", OffsetDateTime.of(2000, 1, 1, 1, 1, 1, 1, ZoneOffset.UTC), new URL(
                                        "GET", "dddd", "dddd"
                                ), "400", 2L, "d", "d"),
                                new LogRecord("1", "1", OffsetDateTime.of(2020, 1, 1, 1, 1, 1, 1, ZoneOffset.UTC), new URL(
                                        "GET", "dddd", "dddd"
                                ), "500", Long.MAX_VALUE, "d", "d"),
                                new LogRecord("1", "1", OffsetDateTime.of(2030, 1, 1, 1, 1, 1, 1, ZoneOffset.UTC), new URL(
                                        "GET", "dddd", "dddd"
                                ), "400", Long.MAX_VALUE, "d", "d"),
                                new LogRecord("1", "1", OffsetDateTime.of(1900, 1, 1, 1, 1, 1, 1, ZoneOffset.UTC), new URL(
                                        "GET", "dddd", "dddd"
                                ), "400", Long.MAX_VALUE, "d", "d")
                        )),
                        getMetric(List.of(400L, 3L, 500L, 1L))
                ),
                Arguments.of(getMetricPopularity(Stream.of()),
                        getMetric(List.of())
                )
        );
    }

    @ParameterizedTest(name = "Index {index} expected array {2}")
    @MethodSource("updateProvider")
    public void update_shouldCorrectlyUpdateMetrics(MetricPopularity metricPopularity, ArrayList<ArrayList<String>> expectedArray) {
        ArrayList<ArrayList<String>> actualArray = metricPopularity.getMetric();

        assertEquals(expectedArray, actualArray);
    }

}
