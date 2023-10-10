package edu.hw1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class Task5Test {

    private static Stream<Arguments> isPalindromeTestMethodProvider() {
        return Stream.of(
            Arguments.of(11, true),
            Arguments.of(121, true),
            Arguments.of(1836829634369286381L, true),
            Arguments.of(1322, true),
            Arguments.of(134, true),
            Arguments.of(1012301112201210L, true),
            Arguments.of(1012301145122012113L, true),
            Arguments.of(12, false),
            Arguments.of(113, false),
            Arguments.of(135, false),
            Arguments.of(1012301112201211L, false),
            Arguments.of(10123011122012113L, false),
            Arguments.of(0, false)
        );
    }

    @ParameterizedTest(name = "Iteration #{index} -> Given palindrome = {0}")
    @MethodSource("isPalindromeTestMethodProvider")
    void isPalindromeTest(long pal, boolean expectedAnswer) {
        assertThat(Task5.isPalindromeDescendant(pal)).isEqualTo(expectedAnswer);
    }
}
