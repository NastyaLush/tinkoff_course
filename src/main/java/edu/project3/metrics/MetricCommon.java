package edu.project3.metrics;

import edu.project3.structures.LogRecord;
import java.math.BigInteger;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class MetricCommon extends Metric {

    private Long countOfRequest = 0L;
    private BigInteger sumOfResponseSize = BigInteger.valueOf(0);
    private OffsetDateTime minDate;
    private OffsetDateTime maxDate;

    public MetricCommon() {
        super("Общая информация");
    }

    @Override
    public void update(LogRecord logRecord) {
        countOfRequest++;
        sumOfResponseSize = sumOfResponseSize.add(BigInteger.valueOf(logRecord.sizeOfResponse()));
        if (minDate == null || maxDate == null) {
            minDate = logRecord.dateOfRequest();
            maxDate = logRecord.dateOfRequest();
        }
        if (minDate.isAfter(logRecord.dateOfRequest())) {
            minDate = logRecord.dateOfRequest();
        }
        if (maxDate.isBefore(logRecord.dateOfRequest())) {
            maxDate = logRecord.dateOfRequest();
        }
    }

    @Override
    public ArrayList<ArrayList<String>> getMetricData() {
        ArrayList<ArrayList<String>> metrics = new ArrayList<>();
        metrics.add(new ArrayList<>(List.of(new String[] {"Метрика", "Значение"})));
        metrics.add(new ArrayList<>(List.of(new String[] {"Начальная дата", getStringPerfom(minDate)})));
        metrics.add(new ArrayList<>(List.of(new String[] {"Конечная дата", getStringPerfom(maxDate)})));
        metrics.add(new ArrayList<>(List.of(new String[] {"Количество запросов", getStringPerfom(countOfRequest)})));
        metrics.add(new ArrayList<>(List.of(new String[] {"Средний размер ответа",
                                                          countOfRequest != 0
                                                              ? sumOfResponseSize.divide(BigInteger.valueOf(
                                                                                     countOfRequest))
                                                                                 .toString() : "0"})));

        return metrics;
    }

    private <T> String getStringPerfom(T t) {
        if (t == null) {
            return "-";
        }
        return t.toString();
    }

}
