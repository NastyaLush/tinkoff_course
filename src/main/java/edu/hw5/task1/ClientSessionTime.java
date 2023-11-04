package edu.hw5.task1;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

public class ClientSessionTime {

    private final DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm");

    @SuppressWarnings("RegexpSinglelineJava")
    public void printDuration(String input) {
        Double avgNanoSecTime = input.lines()
                                     .map(this::getDuration)
                                     .collect(Collectors.averagingLong(Long::longValue));

        Duration duration = Duration.ofNanos(avgNanoSecTime.longValue());
        System.out.printf("%dч %dм", duration.toHours(), duration.toMinutesPart());
    }

    private long getDuration(String input) {
        String[] inputSlitOnStartAndEnd = input.split(" - ");
        LocalDateTime start = LocalDateTime.parse(inputSlitOnStartAndEnd[0], pattern);
        LocalDateTime end = LocalDateTime.parse(inputSlitOnStartAndEnd[1], pattern);
        return Duration.between(start, end)
                       .toNanos();
    }

}
