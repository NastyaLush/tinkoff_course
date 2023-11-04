package edu.hw4.task3;

import edu.hw5.task3.USAFormatDateValidator;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;

public class USAFormatDateValidatorTest {

    public static Stream<Arguments> parseStringProvider() {
        return Stream.of(
                Arguments.of("2020-10-10", Optional.of(LocalDate.of(2020, 10, 10))),
                Arguments.of("2020-10-01", Optional.of(LocalDate.of(2020, 10, 1))),
                Arguments.of("2020-10-1", Optional.of(LocalDate.of(2020, 10, 1))),
                Arguments.of("20-10-1", Optional.empty()),
                Arguments.of("1/3/1976", Optional.empty()),
                Arguments.of("2020-10-0", Optional.empty()),
                Arguments.of("ff", Optional.empty()),
                Arguments.of("today", Optional.empty())
        );
    }

    @ParameterizedTest(name = "parse string should parse date  \"{0}\" in format yyyy-MM-d or return empty optional")
    @MethodSource("parseStringProvider")
    public void parseString_shouldParseDate(String givenString, Optional<LocalDate> expectedOptional) {
        USAFormatDateValidator usaFormatDateValidator = new USAFormatDateValidator();

        Optional<LocalDate> actualOptional = usaFormatDateValidator.parseDate(givenString);

        assertEquals(expectedOptional, actualOptional);
    }

}
