package edu.project3.metrics;

import edu.project3.structures.LogRecord;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MetricPopularity extends Metric {

    private final Integer defaultCountOfRowsToPrint = 3;
    protected final MetricConverter fieldForMetric;
    protected final HashMap<String, Integer> popularity = new HashMap<>();
    protected final Integer countOfRowsToPrint;
    private final String headline;
    private final String popHeadline;

    public MetricPopularity(
        String name,
        String headline,
        String popHeadline,
        MetricConverter fieldForMetric
    ) {
        super(name);
        countOfRowsToPrint = defaultCountOfRowsToPrint;
        this.fieldForMetric = fieldForMetric;
        this.headline = headline;
        this.popHeadline = popHeadline;
    }

    public MetricPopularity(
        String name,
        String headline,
        String popHeadline,
        MetricConverter fieldForMetric,
        Integer countOfRowsToPrint
    ) {
        super(name);
        this.countOfRowsToPrint = countOfRowsToPrint;
        this.fieldForMetric = fieldForMetric;
        this.headline = headline;
        this.popHeadline = popHeadline;
    }

    @Override
    public void update(LogRecord logRecord) {
        if (popularity.containsKey(fieldForMetric.getMetric(logRecord))) {
            popularity.put(
                fieldForMetric.getMetric(logRecord),
                popularity.get(fieldForMetric.getMetric(logRecord)) + 1
            );
        } else {
            popularity.put(fieldForMetric.getMetric(logRecord), 1);
        }
    }

    public ArrayList<ArrayList<String>> getMetricData() {
        Integer currentCountOfRowsToPrint = this.countOfRowsToPrint;
        ArrayList<ArrayList<String>> metrics = new ArrayList<>();
        metrics.add(new ArrayList<>(List.of(new String[] {headline, popHeadline})));
        for (Map.Entry<String, Integer> entry : popularity.entrySet()) {
            metrics.add(new ArrayList<>(List.of(new String[] {entry.getKey(), entry.getValue()
                .toString()})));
            currentCountOfRowsToPrint--;
            if (currentCountOfRowsToPrint == 0) {
                return metrics;
            }
        }
        return metrics;
    }
}
