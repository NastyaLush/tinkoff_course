package edu.hw5.task6;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

public class Task6Test {

    public static Stream<Arguments> task6Provider() {
        return Stream.of(
            Arguments.of("A123BE777", "12", true),
            Arguments.of("A123BE777", "A123BE777", true),
            Arguments.of("A123BE777", "", true),
            Arguments.of("", "", true),
            Arguments.of("A123BE765", "5", true),
            Arguments.of("A123BE765", "A", true),
            Arguments.of("A123BE765", "A15", false),
            Arguments.of("A123BE765", "a", false),
            Arguments.of("A123BE765", "L", false),
            Arguments.of("\\\\abc", "\\\\a", true)

        );
    }

    @ParameterizedTest(name = "is {1} substring in {0}, expected answer{3}")
    @MethodSource("task6Provider")
    public void isHasSubstring_shouldWorkCorrectly(
        String givenMainString,
        String givenPotentialString,
        boolean expectedAnswer
    ) {

        boolean actualAnswer = new Task6().isSubstring(givenMainString, givenPotentialString);

        assertEquals(expectedAnswer, actualAnswer);
    }

}
