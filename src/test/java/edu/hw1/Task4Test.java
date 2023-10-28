package edu.hw1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task4Test {

    @ParameterizedTest(name = "Iteration #{index} -> Given string = {0} and must receive {1}")
    @CsvSource({
        "123456, 214365",
        "hTsii  s aimex dpus rtni.g, This is a mixed up string.",
        "badce, abcde",
        "оПомигети псаривьтс ртко!и, Помогите исправить строки!",
        "be,eb",
        "bad,abd",
        "b,b",
        "aaa,aaa"
    })
    void fixString_shouldWorkCorrect(String input, String output) {
        String ans = Task4.fixString(input);
        assertThat(ans).isEqualTo(output);
    }

    @Test
    void fixString_shouldWorkCorrectWithEmptyStrings() {
        //given
        String data = "";

        //when
        String ans = Task4.fixString(data);
        //then
        assertThat(ans).isEqualTo("");
    }
}
