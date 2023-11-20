package edu.hw5.task3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;

public class DayAgoFormatValidatorTest {

    public static Stream<Arguments> parseStringProvider() {
        return Stream.of(
                Arguments.of("20-10-1", Optional.empty()),
                Arguments.of("2020-10-1", Optional.empty()),
                Arguments.of("1/3/1976", Optional.empty()),
                Arguments.of("tomorrow", Optional.empty()),
                Arguments.of("0 days ago", Optional.of(LocalDate.now())),
                Arguments.of("-1 days ago", Optional.of(LocalDate.now()
                                                                 .plusDays(1))),
                Arguments.of("1 days ago", Optional.of(LocalDate.now()
                                                                .minusDays(1))),
                Arguments.of("111111111111 days ago", Optional.of(LocalDate.now()
                                                                           .minusDays(111111111111L))),
                Arguments.of("9999999999999 days ago", Optional.empty())
        );
    }

    @ParameterizedTest(name = "parse string should parse date \"{0}\" in format x day(s) ago or return empty optional")
    @MethodSource("parseStringProvider")
    public void parseString_shouldParseDate(String givenString, Optional<LocalDate> expectedOptional) {
        DayAgoFormatValidator dayAgoFormatValidator = new DayAgoFormatValidator();

        Optional<LocalDate> actualOptional = dayAgoFormatValidator.parseDate(givenString);

        assertEquals(expectedOptional, actualOptional);
    }

}
