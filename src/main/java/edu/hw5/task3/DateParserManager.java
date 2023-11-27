package edu.hw5.task3;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public class DateParserManager {
    private final List<Parser> parserList = new ArrayList<>();

    public Optional<LocalDate> parse(String date) {
        for (Parser parser : parserList) {
            Optional<LocalDate> dateParsed = parser.parseDate(date);
            if (dateParsed.isPresent()) {
                return dateParsed;
            }
        }
        return Optional.empty();
    }

    public void addParser(@NotNull Parser parser) {
        parserList.add(parser);
    }

    public void removeParser(@NotNull Parser parser) {
        parserList.remove(parser);
    }
}
