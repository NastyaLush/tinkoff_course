package edu.hw5.task3;

public enum DayAround {

    TOMORROW("tomorrow"),
    TODAY("today"),
    YESTERDAY("yesterday");
    private final String name;

    DayAround(String string) {
        this.name = string;
    }

    public String getName() {
        return name;
    }
}
