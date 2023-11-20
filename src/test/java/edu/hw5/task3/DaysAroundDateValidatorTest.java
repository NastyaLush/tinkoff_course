package edu.hw5.task3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;

public class DaysAroundDateValidatorTest {

    public static Stream<Arguments> parseStringProvider() {
        return Stream.of(
                Arguments.of("20-10-1", Optional.empty()),
                Arguments.of("2020-10-1", Optional.empty()),
                Arguments.of("1/3/1976", Optional.empty()),
                Arguments.of("2020-10-0", Optional.empty()),
                Arguments.of("ff", Optional.empty()),
                Arguments.of("today", Optional.of(LocalDate.now())),
                Arguments.of("today 2", Optional.empty()),
                Arguments.of("today2", Optional.empty()),
                Arguments.of("2today", Optional.empty()),
                Arguments.of("2today2", Optional.empty()),
                Arguments.of("yesterday", Optional.of(LocalDate.now()
                                                               .minusDays(1))),
                Arguments.of("tomorrow", Optional.of(LocalDate.now()
                                                              .plusDays(1)))
        );
    }

    @ParameterizedTest(name = "parse string should parse date \"{0}\" in format today, tomorrow, yesterday or return empty optional")
    @MethodSource("parseStringProvider")
    public void parseString_shouldParseDate(String givenString, Optional<LocalDate> expectedOptional) {
        DaysAroundDateValidator daysAroundDateValidator = new DaysAroundDateValidator();

        Optional<LocalDate> actualOptional = daysAroundDateValidator.parseDate(givenString);

        assertEquals(expectedOptional, actualOptional);
    }

}
