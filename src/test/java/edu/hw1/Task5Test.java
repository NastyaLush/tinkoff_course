package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task5Test {
    @Test
    void oneDigit() {
        //data
        long data = 0;
        //when
        boolean ans = Task5.isPalindromeDescendant(data);
        //then
        assertThat(ans).isFalse();
    }
    @Test
    void twoDigitPalindrome() {
        //data
        long data = 11;
        //when
        boolean ans = Task5.isPalindromeDescendant(data);
        //then
        assertThat(ans).isTrue();
    }
    @Test
    void twoDigitNotPalindrome() {
        //data
        long data = 12;
        //when
        boolean ans = Task5.isPalindromeDescendant(data);
        //then
        assertThat(ans).isFalse();
    }
    @Test
    void treeDigitPalindrome() {
        //data
        long data = 121;
        //when
        boolean ans = Task5.isPalindromeDescendant(data);
        //then
        assertThat(ans).isTrue();
    }
    @Test
    void treeDigitNotPalindrome() {
        //data
        long data = 113;
        //when
        boolean ans = Task5.isPalindromeDescendant(data);
        //then
        assertThat(ans).isFalse();
    }
    @Test
    void aLotOfDigitPalindrome() {
        //data
        long data = 1836829634369286381L;
        //when
        boolean ans = Task5.isPalindromeDescendant(data);
        //then
        assertThat(ans).isTrue();
    }
    @Test
    void palindromeSecondChildWithTwoDigits(){
        //data 44
        long data = 1322;
        //when
        boolean ans = Task5.isPalindromeDescendant(data);
        //then
        assertThat(ans).isTrue();
    }
    @Test
    void palindromeSecondChildWithTreeDigits(){
        //data 44
        long data = 134;
        //when
        boolean ans = Task5.isPalindromeDescendant(data);
        //then
        assertThat(ans).isTrue();
    }
    @Test
    void notPalindromeSecondChildWithTreeDigits(){
        //data 45
        long data = 135;
        //when
        boolean ans = Task5.isPalindromeDescendant(data);
        //then
        assertThat(ans).isFalse();
    }
    @Test
    void notPalindromeSecondChildWithTwoDigits(){
        //data 45
        long data = 1341;
        //when
        boolean ans = Task5.isPalindromeDescendant(data);
        //then
        assertThat(ans).isFalse();
    }
    @Test
    void palindromeNChildWithFourDigits(){
        //data 4554
        long data = 1012301112201210L;
        //when
        boolean ans = Task5.isPalindromeDescendant(data);
        //then
        assertThat(ans).isTrue();
    }
    @Test
    void notPalindromeNChildWithFourDigits(){
        //data 4554
        long data = 1012301112201211L;
        //when
        boolean ans = Task5.isPalindromeDescendant(data);
        //then
        assertThat(ans).isFalse();
    }
    @Test
    void notPalindromeNChildWithFourDigitsOdd(){
        //data 4554
        long data = 10123011122012113L;
        //when
        boolean ans = Task5.isPalindromeDescendant(data);
        //then
        assertThat(ans).isFalse();
    }
    @Test
    void palindromeNChildWithTreeDigitsOddSumMoreThan10(){
        //data 121
        long data = 1012301145122012113L;
        //when
        boolean ans = Task5.isPalindromeDescendant(data);
        //then
        assertThat(ans).isTrue();
    }
}
