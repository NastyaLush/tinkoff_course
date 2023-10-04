package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task6Test {
    @Test
    void testsFromExmpl() {
        //data
        int data = 3524;
        //when
        int ans = Task6.countK(data);
        //then
        assertThat(ans).isEqualTo(3);

        //data
        data = 6621;
        //when
        ans = Task6.countK(data);
        //then
        assertThat(ans).isEqualTo(5);

        //data
        data = 6554;
        //when
        ans = Task6.countK(data);
        //then
        assertThat(ans).isEqualTo(4);

        //data
        data = 1234;
        //when
        ans = Task6.countK(data);
        //then
        assertThat(ans).isEqualTo(3);
    }
    @Test
    void testWhenInputIsK() {
        //data
        int data = 6174;
        //when
        int ans = Task6.countK(data);
        //then
        assertThat(ans).isEqualTo(0);
    }
    @Test
    void testWhenInputIsMax() {
        //data
        int data = 9998;
        //when
        int ans = Task6.countK(data);
        //then
        assertThat(ans).isEqualTo(5);
    }
}
