package edu.hw4.task3;

import edu.hw5.task3.DayAgoFormatValidator;
import edu.hw5.task3.DaysAroundDateValidator;
import edu.hw5.task3.EuropeanFormatDateValidator;
import edu.hw5.task3.EuropeanFormatDateValidatorWithShortYear;
import edu.hw5.task3.USAFormatDateValidator;
import edu.hw5.task3.Validator;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;

public class Task3Test {

    public static Stream<Arguments> parseStringProvider() {
        return Stream.of(
                Arguments.of("2020-10-10", Optional.of(LocalDate.of(2020, 10, 10))),
                Arguments.of("2020-10-01", Optional.of(LocalDate.of(2020, 10, 1))),
                Arguments.of("2020-10-1", Optional.of(LocalDate.of(2020, 10, 1))),
                Arguments.of("0 days ago", Optional.of(LocalDate.now())),
                Arguments.of("-1 days ago", Optional.of(LocalDate.now()
                                                                 .plusDays(1))),
                Arguments.of("1 days ago", Optional.of(LocalDate.now()
                                                                .minusDays(1))),
                Arguments.of("111111111111 days ago", Optional.of(LocalDate.now()
                                                                           .minusDays(111111111111L))),
                Arguments.of("yesterday", Optional.of(LocalDate.now()
                                                               .minusDays(1))),
                Arguments.of("tomorrow", Optional.of(LocalDate.now()
                                                              .plusDays(1))),
                Arguments.of("today", Optional.of(LocalDate.now())),
                Arguments.of("1/3/1976", Optional.of(LocalDate.of(1976, 3, 1))),
                Arguments.of("10/3/1976", Optional.of(LocalDate.of(1976, 3, 10))),
                Arguments.of("10/12/1976", Optional.of(LocalDate.of(1976, 12, 10))),
                Arguments.of("10/12/76", Optional.of(LocalDate.of(2076, 12, 10))),
                Arguments.of("10/2/76", Optional.of(LocalDate.of(2076, 2, 10))),
                Arguments.of("1/12/76", Optional.of(LocalDate.of(2076, 12, 1))),
                Arguments.of("12.03.2004", Optional.empty()),
                Arguments.of("jjjjjjjjjjjjjjjjjjjjjjjj days ago", Optional.empty())

        );
    }

    @ParameterizedTest(name = "parse string should parse date  \"{0}\" in format all format of Validators or return empty optional")
    @MethodSource("parseStringProvider")
    public void parseString_shouldCorrectlyParseString(String givenString, Optional<LocalDate> expectedOptional) {
        Validator chain = createChain();
        Optional<LocalDate> actualOptional;
        do {
            actualOptional = chain.parseDate(givenString);
            chain = chain.getNextValidator();
        } while (actualOptional.isEmpty() && chain != null);

        assertEquals(expectedOptional, actualOptional);
    }

    private Validator createChain() {
        USAFormatDateValidator usaFormatDateValidator = new USAFormatDateValidator();
        EuropeanFormatDateValidator europeanFormatDateValidator = new EuropeanFormatDateValidator();
        EuropeanFormatDateValidatorWithShortYear europeanFormatDateValidatorWithShortYear = new EuropeanFormatDateValidatorWithShortYear();
        DaysAroundDateValidator daysAroundDateValidator = new DaysAroundDateValidator();
        DayAgoFormatValidator dayAgoFormatValidator = new DayAgoFormatValidator();

        daysAroundDateValidator.setNextValidator(dayAgoFormatValidator);
        europeanFormatDateValidatorWithShortYear.setNextValidator(daysAroundDateValidator);
        europeanFormatDateValidator.setNextValidator(europeanFormatDateValidatorWithShortYear);
        usaFormatDateValidator.setNextValidator(europeanFormatDateValidator);

        return usaFormatDateValidator;
    }

}
