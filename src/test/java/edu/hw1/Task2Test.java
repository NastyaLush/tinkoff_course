package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task2Test {
    @Test
    void Figure(){
        //data
        int n=0;
        //when
        int ans=Task2.countDigits(n);
        //then
        assertThat(ans).isEqualTo(1);
    }
    @Test
    void Number(){
        //data
        int n=10;
        //when
        int ans=Task2.countDigits(n);
        //then
        assertThat(ans).isEqualTo(2);
    }
    @Test
    void NegativeFigure(){
        //data
        int n=-1;
        //when
        int ans=Task2.countDigits(n);
        //then
        assertThat(ans).isEqualTo(1);
    }
    @Test
    void NegativeNumber(){
        //data
        int n=-11;
        //when
        int ans=Task2.countDigits(n);
        //then
        assertThat(ans).isEqualTo(2);
    }
    @Test
    void NegativeZero(){
        //data
        int n=-0;
        //when
        int ans=Task2.countDigits(n);
        //then
        assertThat(ans).isEqualTo(1);
    }
    @Test
    void LongNumber(){
        //data
        long n=Integer.MAX_VALUE+5L;
        //when
        int ans=Task2.countDigits(n);
        //then
        assertThat(ans).isEqualTo(10);
    }
}
