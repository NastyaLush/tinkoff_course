package edu.hw1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task6Test {
    @ParameterizedTest(name = "Iteration #{index} -> Given number = {0} and must receive {1}")
    @CsvSource({
        "3524, 3",
        "6621, 5",
        "6554, 4",
        "1234, 3",
        "6174, 0",
        "9998, 5"

    })
    void testWhenInputIsLessThan1000(int data, int ans) {
        assertThat(Task6.countK(data)).isEqualTo(ans);
    }

    @ParameterizedTest(name = "Iteration #{index} -> Given number = {0} and must receive error")
    @ValueSource(ints = {999, 8888})
    void countK_shouldWorkCorrectlyWithWrongData(int data) {
        assertThrows(
            IllegalArgumentException.class,
            () -> Task6.countK(data),
            "Expected countK() to throw, but it didn't"
        );
    }
}
