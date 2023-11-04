package edu.hw4.task3;

import edu.hw5.task3.EuropeanFormatDateValidator;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;

public class EuropeanFormatDateValidatorTest {

    public static Stream<Arguments> parseStringProvider() {
        return Stream.of(
                Arguments.of("2020-10-10", Optional.empty()),
                Arguments.of("2020-10-01", Optional.empty()),
                Arguments.of("2020-10-1", Optional.empty()),
                Arguments.of("1/3/1976", Optional.of(LocalDate.of(1976, 3, 1))),
                Arguments.of("10/3/1976", Optional.of(LocalDate.of(1976, 3, 10))),
                Arguments.of("10/12/1976", Optional.of(LocalDate.of(1976, 12, 10))),
                Arguments.of("10/12/76", Optional.empty()),
                Arguments.of("1/30/1976", Optional.empty()),
                Arguments.of("2020-10-0", Optional.empty()),
                Arguments.of("ff", Optional.empty()),
                Arguments.of("today", Optional.empty())
        );
    }

    @ParameterizedTest(name = "parse string should parse date  \"{0}\" in format d/M/yyyy or return empty optional")
    @MethodSource("parseStringProvider")
    public void parseString_shouldParseDate(String givenString, Optional<LocalDate> expectedOptional) {
        EuropeanFormatDateValidator europeanFormatDateValidator = new EuropeanFormatDateValidator();

        Optional<LocalDate> actualOptional = europeanFormatDateValidator.parseDate(givenString);

        assertEquals(expectedOptional, actualOptional);
    }

}
