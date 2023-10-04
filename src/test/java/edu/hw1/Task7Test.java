package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task7Test {
    @Test
    void exmplTests() {
        //data
        int n = 8;
        int shift=1;
        //when
        int ans = Task7.rotateRight(n,shift);
        //then
        assertThat(ans).isEqualTo(4);

        //data
        n = 16;
        shift=1;
        //when
        ans = Task7.rotateLeft(n,shift);
        //then
        assertThat(ans).isEqualTo(1);

        //data
        n = 17;
        shift=2;
        //when
        ans = Task7.rotateLeft(n,shift);
        //then
        assertThat(ans).isEqualTo(6);
    }
    @Test
    @DisplayName("сдвиг вправо на количесво цифр в числе")
    void testRight1() {
        //data
        int n = 47583;
        int shift=16;
        //when
        int ans = Task7.rotateRight(n,shift);
        //then
        assertThat(ans).isEqualTo(47583);
    }
    @Test
    @DisplayName("сдвиг вправо на количесво цифр в четном числе плюс 1")
    void testRight2() {
        //data
        int n = 47582;
        int shift=17;
        //when
        int ans = Task7.rotateRight(n,shift);
        //then
        assertThat(ans).isEqualTo(47582/2);
    }
    @Test
    @DisplayName("сдвиг вправо  на количесво цифр в нечетном числе плюс 1")
    void testRight3() {
        //data
        int n = 47583;
        int shift=17;
        //when
        int ans = Task7.rotateRight(n,shift);
        //then
        assertThat(ans).isEqualTo(56559);
    }
    @Test
    @DisplayName("сдвиг вправо 0 на много")
    void testRight4() {
        //data
        int n = 0;
        int shift=5243;
        //when
        int ans = Task7.rotateRight(n,shift);
        //then
        assertThat(ans).isEqualTo(0);
    }
    @Test
    @DisplayName("сдвиг влево на количесво цифр в числе")
    void testLeft1() {
        //data
        int n = 47583;
        int shift=16;
        //when
        int ans = Task7.rotateLeft(n,shift);
        //then
        assertThat(ans).isEqualTo(47583);
    }
    @Test
    @DisplayName("сдвиг влево на количесво цифр в четном числе плюс 1")
    void testLeft2() {
        //data
        int n = 47582;
        int shift=17;
        //when
        int ans = Task7.rotateLeft(n,shift);
        //then
        assertThat(ans).isEqualTo(29629);
    }
    @Test
    @DisplayName("сдвиг влево на количесво цифр в нечетном числе плюс 1")
    void testLeft3() {
        //data
        int n = 47583;
        int shift=17;
        //when
        int ans = Task7.rotateLeft(n,shift);
        //then
        assertThat(ans).isEqualTo(29631);
    }
    @Test
    @DisplayName("сдвиг влево 0 на много")
    void testLeft4() {
        //data
        int n = 0;
        int shift=5243;
        //when
        int ans = Task7.rotateLeft(n,shift);
        //then
        assertThat(ans).isEqualTo(0);
    }
}
