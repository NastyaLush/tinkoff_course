package edu.project3.metrics;

import edu.project3.structures.LogRecord;

@FunctionalInterface
public interface FieldForMetric {

    String getField(LogRecord logRecord);
}
