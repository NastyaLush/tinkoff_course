package edu.hw5.task2;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;

public class Friday13 {

    private static final long MAX_YEAR_ALLOWED = 999999999;
    private final Integer dayOfFriday13 = 13;
    private final Integer countOfMonthInYear = 12;
    private final Integer countOfDaysInWeak = 7;
    private final Integer countOfDaysInDecember = 31;


    public ArrayList<LocalDate> getFridays13OfGivenYear(Integer year) {
        validateYear(year);
        ArrayList<LocalDate> fridays = new ArrayList<>();
        LocalDate current = LocalDate.of(year, 1, dayOfFriday13);
        if (current.getDayOfWeek() == DayOfWeek.FRIDAY) {
            fridays.add(current);
        }
        do {
            current = current.plusMonths(1);
            if (current.getDayOfWeek() == DayOfWeek.FRIDAY) {
                fridays.add(current);
            }
        } while (current.getMonthValue() != countOfMonthInYear);

        return fridays;
    }

    private void validateYear(Integer year) {
        if (year < -MAX_YEAR_ALLOWED || year > MAX_YEAR_ALLOWED) {
            throw new IllegalArgumentException();
        }
    }

    public LocalDate getNextFriday13(LocalDate current) {
        LocalDate nextDay13 = current;
        do {
            nextDay13 = nextDay13.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        } while (nextDay13.getDayOfMonth() != dayOfFriday13
                && !(nextDay13.getYear() == MAX_YEAR_ALLOWED && nextDay13.getMonth() == Month.DECEMBER
                && nextDay13.getDayOfMonth() + countOfDaysInWeak > countOfDaysInDecember));
        if (nextDay13.getDayOfMonth() == dayOfFriday13) {
            return nextDay13;
        }
        return null;

    }


}
