package edu.hw1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class Task5Test {
    @ParameterizedTest(name = "Iteration #{index} -> Given pal = {0}")
    @ValueSource(longs = {11, 121, 1836829634369286381L})
    void isPalindromeDescendant_shouldCorrectlyFindPalindrome(long pal) {
        boolean ans = Task5.isPalindromeDescendant(pal);
        assertThat(ans).isTrue();
    }

    @ParameterizedTest(name = "Iteration #{index} -> Given pal = {0}")
    @ValueSource(longs = {1322, 134, 1012301112201210L, 1012301145122012113L})
    void isPalindromeDescendant_shouldCorrectlyFindPalindromeChild(long pal) {
        boolean ans = Task5.isPalindromeDescendant(pal);
        assertThat(ans).isTrue();
    }

    @ParameterizedTest(name = "Iteration #{index} -> Given pal = {0}")
    @ValueSource(longs = {12})
    void isPalindromeDescendant_shouldCorrectlyNotFindPalindrome(long pal) {
        boolean ans = Task5.isPalindromeDescendant(pal);
        assertThat(ans).isFalse();
    }

    @ParameterizedTest(name = "Iteration #{index} -> Given pal = {0}")
    @ValueSource(longs = {113, 135, 1012301112201211L, 10123011122012113L})
    void isPalindromeDescendant_shouldCorrectlyNotFindPalindromeAndChild(long pal) {
        boolean ans = Task5.isPalindromeDescendant(pal);
        assertThat(ans).isFalse();
    }

    @ParameterizedTest(name = "Iteration #{index} -> Given pal = {0}")
    @ValueSource(longs = {0})
    void isPalindromeDescendant_shouldNotWorkWithOneDigit(long pal) {
        boolean ans = Task5.isPalindromeDescendant(pal);
        assertThat(ans).isFalse();
    }
}
