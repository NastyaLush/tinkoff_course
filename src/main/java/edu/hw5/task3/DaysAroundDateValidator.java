package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;

public class DaysAroundDateValidator extends Validator {

    public DaysAroundDateValidator() {
        super(null);
    }

    @Override
    public Optional<LocalDate> parseDate(String string) {
        if (string.equals(DayAround.TOMORROW.getName())) {
            return Optional.of(LocalDate.now()
                                        .plusDays(1));
        }
        if (string.equals(DayAround.TODAY.getName())) {
            return Optional.of(LocalDate.now());
        }
        if (string.equals(DayAround.YESTERDAY.getName())) {
            return Optional.of(LocalDate.now()
                                        .minusDays(1));
        }
        return Optional.empty();
    }
}
