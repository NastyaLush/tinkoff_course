package edu.hw5.task7;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

public class Task7Test {

    public static Stream<Arguments> lengthMoreOrEqual3AndThirdSymbol0Provider() {
        return Stream.of(
                Arguments.of("00000000", true),
                Arguments.of("000", true),
                Arguments.of("0010", false),
                Arguments.of("00", false),
                Arguments.of("h000", false),
                Arguments.of("1111", false),
                Arguments.of("1101", true)
        );
    }

    public static Stream<Arguments> beginsAndEndsWithOneLetterProvider() {
        return Stream.of(
                Arguments.of("00000000", true),
                Arguments.of("000", true),
                Arguments.of("0", true),
                Arguments.of("00", true),
                Arguments.of("1", true),
                Arguments.of("10010010101", true),
                Arguments.of("010101010", true),
                Arguments.of("0h00", false),
                Arguments.of("1110", false),
                Arguments.of("0101", false)
        );
    }

    public static Stream<Arguments> lengthBetween1and3Provider() {
        return Stream.of(
                Arguments.of("00000000", false),
                Arguments.of("000", true),
                Arguments.of("0", true),
                Arguments.of("00", true),
                Arguments.of("1", true),
                Arguments.of("10", true),
                Arguments.of("0h0", false),
                Arguments.of("", false)
        );
    }

    @ParameterizedTest(name = "The word should have string size more or equal 3 and third symbol is 0, given word {0}")
    @MethodSource("lengthMoreOrEqual3AndThirdSymbol0Provider")
    public void lengthMoreOrEqual3AndThirdSymbol0_shouldReturnTrueIfLengthMoreOrEqual3AndThirdSymbol0(String givenString, boolean expectedAnswer) {
        Task7 task7 = new Task7();

        boolean actualAnswer = task7.lengthMoreOrEqual3AndThirdSymbol0(givenString);

        assertEquals(expectedAnswer, actualAnswer);
    }

    @ParameterizedTest(name = "The word should begins and ends with the same letter, given word {0}")
    @MethodSource("beginsAndEndsWithOneLetterProvider")
    public void beginsAndEndsWithOneLetter_shouldReturnTrueIfStringBeginsAndEndsWithTheSameLetter(String givenString, boolean expectedAnswer) {
        Task7 task7 = new Task7();

        boolean actualAnswer = task7.beginsAndEndsWithOneLetter(givenString);

        assertEquals(expectedAnswer, actualAnswer);
    }

    @ParameterizedTest(name = "The word length between 1 and 3, given word {0}")
    @MethodSource("lengthBetween1and3Provider")
    public void lengthBetween1and3_shouldReturnTrueIfLengthBetween1and3(String givenString, boolean expectedAnswer) {
        Task7 task7 = new Task7();

        boolean actualAnswer = task7.lengthBetween1and3(givenString);

        assertEquals(expectedAnswer, actualAnswer);
    }
}
