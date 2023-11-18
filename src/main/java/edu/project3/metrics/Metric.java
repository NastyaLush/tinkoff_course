package edu.project3.metrics;

import edu.project3.structures.LogRecord;
import java.util.ArrayList;

public abstract class Metric {

    private final String name;

    protected Metric(String name) {
        this.name = name;
    }

    public abstract void update(LogRecord logRecord);

    public String getHeadline() {
        return name;
    }

    public abstract ArrayList<ArrayList<String>> getMetric();

}
