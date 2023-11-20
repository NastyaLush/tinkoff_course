package edu.hw5.task2;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalField;
import java.util.ArrayList;
import java.util.List;
import java.time.temporal.ChronoUnit;
import java.time.temporal.IsoFields;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalUnit;

import java.time.temporal.ChronoField;

public class Friday13 {

    private static final long MAX_YEAR_ALLOWED = 999999999;
    private final Integer dayOfFriday13 = 13;
    private final Integer countOfMonthInYear = 12;
    private final Integer countOfDaysInWeek = 7;
    private final Integer countOfDaysInDecember = 31;

    public List<LocalDate> getFridays13OfGivenYear(Integer year) {
        validateYear(year);
        ArrayList<LocalDate> fridays = new ArrayList<>();
        LocalDate current = LocalDate.of(year, 1, dayOfFriday13);
        while (current.getYear() == year) {
            if (current.getDayOfWeek() == DayOfWeek.FRIDAY) {
                fridays.add(current);
            }
            if (current.getYear() == MAX_YEAR_ALLOWED && current.getMonth() == Month.DECEMBER) {
                return fridays;
            }
            current = current.plusMonths(1);
        }

        return fridays;
    }

    private void validateYear(Integer year) {
        if (year < -MAX_YEAR_ALLOWED || year > MAX_YEAR_ALLOWED) {
            throw new IllegalArgumentException();
        }
    }

    public LocalDate getNextFriday13(LocalDate current) {
        LocalDate nextDay13 = current.withDayOfMonth(dayOfFriday13);
        if (nextDay13.getYear() == MAX_YEAR_ALLOWED
            && nextDay13.getMonth() == Month.DECEMBER) {
            return null;
        }
        if (nextDay13.isBefore(current) || nextDay13.isEqual(current)) {
            nextDay13 = nextDay13.plusMonths(1);
        }
        while (nextDay13.getDayOfWeek() != DayOfWeek.FRIDAY
            && !(nextDay13.getYear() == MAX_YEAR_ALLOWED
            && nextDay13.getMonth() == Month.DECEMBER)) {

            nextDay13 = nextDay13.plusMonths(1);

        }
        if (nextDay13.getDayOfMonth() == dayOfFriday13) {
            return nextDay13;
        }
        return null;

    }

}
