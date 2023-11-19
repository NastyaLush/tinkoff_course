package project3.metricsTest;

import edu.project3.metrics.MetricCommon;
import edu.project3.structures.LogRecord;
import edu.project3.structures.URL;
import org.assertj.core.data.Index;
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

public class MetricCommonTest {

    public static Stream<Arguments> updateProvider() {
        return Stream.of(
                Arguments.of(getMetricCommon(Stream.of(
                                new LogRecord("1", "1", OffsetDateTime.of(2000, 1, 1, 1, 1, 1, 1, ZoneOffset.UTC), new URL(
                                        "GET", "dddd", "dddd"
                                ), "400", 2L, "d", "d")
                        )),
                        getMetric(OffsetDateTime.of(2000, 1, 1, 1, 1, 1, 1, ZoneOffset.UTC)
                                                .toString(),
                                OffsetDateTime.of(2000, 1, 1, 1, 1, 1, 1, ZoneOffset.UTC)
                                              .toString(),
                                1L, BigInteger.valueOf(2))
                ),
                Arguments.of(getMetricCommon(Stream.of(
                                new LogRecord("1", "1", OffsetDateTime.of(2000, 1, 1, 1, 1, 1, 1, ZoneOffset.UTC), new URL(
                                        "GET", "dddd", "dddd"
                                ), "400", 2L, "d", "d"),
                                new LogRecord("1", "1", OffsetDateTime.of(2020, 1, 1, 1, 1, 1, 1, ZoneOffset.UTC), new URL(
                                        "GET", "dddd", "dddd"
                                ), "400", Long.MAX_VALUE, "d", "d"),
                                new LogRecord("1", "1", OffsetDateTime.of(2030, 1, 1, 1, 1, 1, 1, ZoneOffset.UTC), new URL(
                                        "GET", "dddd", "dddd"
                                ), "400", Long.MAX_VALUE, "d", "d"),
                                new LogRecord("1", "1", OffsetDateTime.of(1900, 1, 1, 1, 1, 1, 1, ZoneOffset.UTC), new URL(
                                        "GET", "dddd", "dddd"
                                ), "400", Long.MAX_VALUE, "d", "d")
                        )),
                        getMetric(OffsetDateTime.of(1900, 1, 1, 1, 1, 1, 1, ZoneOffset.UTC)
                                                .toString(),
                                OffsetDateTime.of(2030, 1, 1, 1, 1, 1, 1, ZoneOffset.UTC)
                                              .toString(),
                                4L, BigInteger.valueOf(Long.MAX_VALUE)
                                              .multiply(BigInteger.valueOf(3))
                                              .add(BigInteger.valueOf(1)))
                ),
                Arguments.of(getMetricCommon(Stream.of()),
                        getMetric("-",
                                "-",
                                0L, BigInteger.valueOf(0L))
                )
        );
    }

    private static MetricCommon getMetricCommon(Stream<LogRecord> logRecordStream) {
        MetricCommon metricCommon = new MetricCommon();
        logRecordStream.forEach((metricCommon::update));
        return metricCommon;
    }

    private static ArrayList<ArrayList<String>> getMetric(String minDate, String maxDate, Long countOfRequest, BigInteger sumOfResponseSize) {
        ArrayList<ArrayList<String>> metrics = new ArrayList<>();
        metrics.add(new ArrayList<>(List.of(new String[]{"Метрика", "Значение"})));
        metrics.add(new ArrayList<>(List.of(new String[]{"Начальная дата", minDate.toString()})));
        metrics.add(new ArrayList<>(List.of(new String[]{"Конечная дата", maxDate.toString()})));
        metrics.add(new ArrayList<>(List.of(new String[]{"Количество запросов", countOfRequest.toString()})));
        metrics.add(new ArrayList<>(List.of(new String[]{"Средний размер ответа",
                countOfRequest != 0 ? sumOfResponseSize.divide(BigInteger.valueOf(countOfRequest))
                                                       .toString() : "0"})));

        return metrics;
    }

    @ParameterizedTest(name = "Index {index} expected answer {2}")
    @MethodSource("updateProvider")
    public void update_shouldUpdateMetrics(MetricCommon metricCommon, ArrayList<ArrayList<String>> expectedArray) {
        ArrayList<ArrayList<String>> actualArray = metricCommon.getMetric();

        assertEquals(expectedArray, actualArray);
    }

}
