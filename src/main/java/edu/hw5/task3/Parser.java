package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;

public interface Parser {
    Optional<LocalDate> parseDate(String string);
}
