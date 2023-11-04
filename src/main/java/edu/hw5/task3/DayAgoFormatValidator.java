package edu.hw5.task3;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DayAgoFormatValidator extends Validator {

    public DayAgoFormatValidator() {
        super(null);
    }

    @Override
    public Optional<LocalDate> parseDate(String string) {
        Pattern pattern = Pattern.compile("^[-+]?\\d+ days? ago$");
        Matcher matcher = pattern.matcher(string);

        if (matcher.find()) {
            try {
                return Optional.of(LocalDate.now()
                                            .minusDays(Long.parseLong(string.split(" ")[0])));
            } catch (DateTimeException | NumberFormatException e) {
                return Optional.empty();
            }
        }
        return Optional.empty();
    }
}
