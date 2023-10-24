package edu.hw1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {

    @ParameterizedTest(name = "Iteration #{index} -> Given time = {0} and the multiplication seconds is {1}")
    @CsvSource({
        "12:12, 732",
        "123:40, 7420",
        "123:60, 7440",
        "0:60,60"
    })
    void minutesToSeconds_shouldConvertToSecondsIfDataIsValid(String time, int answer) {
        long min = Task1.minutesToSeconds(time);
        assertThat(min).isEqualTo(answer);
    }

    @Test
    void minutesToSeconds_shouldConvertToSecondsIfMinutesMoreThanInt() {
        //given
        Long intM = Integer.MAX_VALUE + 5L;
        String data = intM + ":23";

        //when
        long min = Task1.minutesToSeconds(data);
        //then
        assertThat(min).isEqualTo(intM * 60 + 23);
    }

    @ParameterizedTest(name = "Iteration #{index} -> Given time = {0}")
    @ValueSource(strings = {
        "123:61",
        "123:6b",
        "123:-06",
        "-123:06",
        "b:06",
        ":06",
        "11:",
        ":",
        "-1:60"
    })
    void minutesToSeconds_shouldConvertToSecondsIfDataIsInvalid(String data) {
        //when
        long min = Task1.minutesToSeconds(data);
        //then
        assertThat(min).isEqualTo(-1);
    }

}
