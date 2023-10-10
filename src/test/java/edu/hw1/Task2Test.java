package edu.hw1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class Task2Test {
    @ParameterizedTest(name = "Iteration #{index} -> Given number = {0} and count of digits is {1}")
    @CsvSource({
        "0, 1",
        "10, 2",
        "-1, 1",
        "-1, 1"
    })
    void countDigits_shouldCountDigits(int n, int res) {
        int ans = Task2.countDigits(n);
        assertThat(ans).isEqualTo(res);
    }

    @Test
    void LongNumber() {
        //given
        long n = Integer.MAX_VALUE + 5L;

        //when
        int ans = Task2.countDigits(n);
        //then
        assertThat(ans).isEqualTo(Long.toString(n).length());
    }
}
