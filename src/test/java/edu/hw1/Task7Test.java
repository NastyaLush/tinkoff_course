package edu.hw1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class Task7Test {
    @ParameterizedTest(name = "Iteration #{index} -> Given number = {0} and must receive {1}")
    @CsvSource({
        "8, 1, 4",
        "47583, 16, 47583",
        "47582, 17, 23791",
        "47583, 17, 56559",
        "0, 5243, 0"

    })
    void rotateRight_shouldWorkCorrectly(int n, int shift, int ans) {
        assertThat(Task7.rotateRight(n, shift)).isEqualTo(ans);
    }

    @ParameterizedTest(name = "Iteration #{index} -> Given number = {0} and must receive {1}")
    @CsvSource({
        "16, 1, 1",
        "17, 2, 6",
        "47583, 16, 47583",
        "47582, 17, 29629",
        "47583, 17, 29631",
        "0, 5232, 0"

    })
    void rotateLeft_shouldWorkCorrectly(int n, int shift, int ans) {
        assertThat(Task7.rotateLeft(n, shift)).isEqualTo(ans);
    }

}
