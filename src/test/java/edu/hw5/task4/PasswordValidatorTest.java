package edu.hw5.task4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

public class PasswordValidatorTest {

    public static Stream<Arguments> passwordProvider() {
        return Stream.of(
                Arguments.of("~hhhh", true),
                Arguments.of("h~hhh", true),
                Arguments.of("hhhh~", true),
                Arguments.of("~", true),
                Arguments.of("d!d", true),
                Arguments.of("d@d", true),
                Arguments.of("d#d", true),
                Arguments.of("d$d", true),
                Arguments.of("d%d", true),
                Arguments.of("d^d", true),
                Arguments.of("d&d", true),
                Arguments.of("d*d", true),
                Arguments.of("d|d", true),
                Arguments.of("hhhh", false),
                Arguments.of("~~", false),
                Arguments.of("~d~", false),
                Arguments.of("d~~d", false),
                Arguments.of("d~d~d", false),
                Arguments.of("d!~d", false)

        );
    }

    @ParameterizedTest(name = "is valid answer should check if {0} valid")
    @MethodSource("passwordProvider")
    public void isValidAnswer_shouldWorkCorrectly(String givenPassword, boolean expectedAnswer) {

        boolean actualAnswer = new PasswordValidator().isValidPassword(givenPassword);

        assertEquals(expectedAnswer, actualAnswer);
    }

}
