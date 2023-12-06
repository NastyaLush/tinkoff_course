package edu.hw9.task1;

import java.util.Objects;
import java.util.concurrent.locks.ReentrantLock;
import lombok.Getter;

public class Metric {

    @Getter private final String name;
    private final ReentrantLock reentrantLock = new ReentrantLock();
    @Getter private double sum = 0;
    @Getter private double max = -Double.MAX_VALUE;
    @Getter private double min = Double.MAX_VALUE;
    private long count = 0;

    public Metric(String name) {
        this.name = name;
    }

    public Metric(String name, double sum, double max, double min, long count) {
        this.name = name;
        this.sum = sum;
        this.max = max;
        this.min = min;
        this.count = count;
    }

    public void addSum(double sum) {
        reentrantLock.lock();
        try {
            this.sum += sum;
        } finally {
            reentrantLock.unlock();
        }
    }

    public void addCount(long count) {
        reentrantLock.lock();
        try {
            this.count += count;
        } finally {
            reentrantLock.unlock();
        }
    }

    public void addMax(double max) {
        reentrantLock.lock();
        try {
            if (max > this.max) {
                this.max = max;
            }
        } finally {
            reentrantLock.unlock();
        }
    }

    public void addMin(double min) {
        reentrantLock.lock();
        try {
            if (min < this.min) {
                this.min = min;
            }
        } finally {
            reentrantLock.unlock();
        }
    }

    public double getAverage() {
        return sum / count;
    }

    @Override public String toString() {
        return "Metric: " + name
            + "\nsum=" + sum
            + "\naverage=" + sum / count
            + "\nmax=" + max
            + "\nmin=" + min;
    }

    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Metric metric)) {
            return false;
        }
        return Double.compare(sum, metric.sum) == 0 && Double.compare(count, metric.count) == 0
            && Double.compare(max, metric.max) == 0 && Double.compare(min, metric.min) == 0 && Objects.equals(
            name,
            metric.name
        );
    }

    @Override public int hashCode() {
        return Objects.hash(name, sum, count, max, min);
    }
}
