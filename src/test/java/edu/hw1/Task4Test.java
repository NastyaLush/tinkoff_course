package edu.hw1;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Task4Test {
    @Test
    void test1FromExmpl() {
        //data
        String data = "123456";
        //when
        String ans = Task4.fixString(data);
        //then
        assertThat(ans).isEqualTo("214365");
    }

    @Test
    void test2FromExmpl() {
        //data
        String data = "hTsii  s aimex dpus rtni.g";
        //when
        String ans = Task4.fixString(data);
        //then
        assertThat(ans).isEqualTo("This is a mixed up string.");
    }

    @Test
    void test3FromExmpl() {
        //data
        String data = "badce";
        //when
        String ans = Task4.fixString(data);
        //then
        assertThat(ans).isEqualTo("abcde");
    }

    @Test
    void test4FromExmpl() {
        //data
        String data = "оПомигети псаривьтс ртко!и";
        //when
        String ans = Task4.fixString(data);
        //then
        assertThat(ans).isEqualTo("Помогите исправить строки!");
    }

    @Test
    void emptyStr() {
        //data
        String data = "";
        //when
        String ans = Task4.fixString(data);
        //then
        assertThat(ans).isEqualTo("");
    }

    @Test
    void stringWith2Letters() {
        //data
        String data = "be";
        //when
        String ans = Task4.fixString(data);
        //then
        assertThat(ans).isEqualTo("eb");
    }
    @Test
    void stringWith3Letters() {
        //data
        String data = "bad";
        //when
        String ans = Task4.fixString(data);
        //then
        assertThat(ans).isEqualTo("abd");
    }
    @Test
    void stringWith1Letter() {
        //data
        String data = "b";
        //when
        String ans = Task4.fixString(data);
        //then
        assertThat(ans).isEqualTo("b");
    }
    @Test
    void stringWith3SameLetters() {
        //data
        String data = "aaa";
        //when
        String ans = Task4.fixString(data);
        //then
        assertThat(ans).isEqualTo("aaa");
    }
    @Test
    void stringWith2SameLetters() {
        //data
        String data = "aa";
        //when
        String ans = Task4.fixString(data);
        //then
        assertThat(ans).isEqualTo("aa");
    }
    @Test
    void stringWith7DiffLetters() {
        //data
        String data = "abcdefg";
        //when
        String ans = Task4.fixString(data);
        //then
        assertThat(ans).isEqualTo("badcfeg");
    }
}
