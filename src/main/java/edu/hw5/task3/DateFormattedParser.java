package edu.hw5.task3;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class DateFormattedParser implements Parser {

    protected final DateTimeFormatter formatter;

    public DateFormattedParser(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    public Optional<LocalDate> parseDate(String string) {
        try {
            LocalDate localDate = LocalDate.parse(string, formatter);
            return Optional.of(localDate);
        } catch (DateTimeException e) {
            return Optional.empty();
        }
    }

}
