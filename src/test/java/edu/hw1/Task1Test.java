package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @Test
    void correctData(){
        //given
        String data="12:12";
        //when
        long min = Task1.minutesToSeconds(data);
        //then
        assertThat(min).isEqualTo(12*60+12);
    }
    @Test
    void correctDataZero(){
        //given
        String data="00:00";
        //when
        long min = Task1.minutesToSeconds(data);
        //then
        assertThat(min).isEqualTo(0);
    }
    @Test
    void correctDataMinutesMore60(){
        //given
        String data="123:40";
        //when
        long min = Task1.minutesToSeconds(data);
        //then
        assertThat(min).isEqualTo(123*60+40);
    }
    @Test
    void correctDataMaxSec(){
        //given
        String data="123:60";
        //when
        long min = Task1.minutesToSeconds(data);
        //then
        assertThat(min).isEqualTo(123*60+60);
    }
    @Test
    void correctDataMinutesMoreThanInt(){
        //given
        Long intM=Integer.MAX_VALUE+5L;
        String data=String.valueOf(intM)+":23";
        //when
        System.out.println(intM);
        long min = Task1.minutesToSeconds(data);
        //then
        assertThat(min).isEqualTo(intM*60+23);
    }
    @Test
    void incorrectDataSecondsMore60(){
        //given
        String data="123:61";
        //when
        long min = Task1.minutesToSeconds(data);
        //then
        assertThat(min).isEqualTo(-1);
    }
    @Test
    void incorrectDataSecondsHasChar(){
        //given
        String data="123:6b";
        //when
        long min = Task1.minutesToSeconds(data);
        //then
        assertThat(min).isEqualTo(-1);
    }
    @Test
    void incorrectDataSecondsHasMinus(){
        //given
        String data="123:-06";
        //when
        long min = Task1.minutesToSeconds(data);
        //then
        assertThat(min).isEqualTo(-1);
    }
    @Test
    void incorrectDataMinutesHasMinus(){
        //given
        String data="-123:06";
        //when
        long min = Task1.minutesToSeconds(data);
        //then
        assertThat(min).isEqualTo(-1);
    }
    @Test
    void incorrectDataMinutesHasChar(){
        //given
        String data="b:06";
        //when
        long min = Task1.minutesToSeconds(data);
        //then
        assertThat(min).isEqualTo(-1);
    }
    @Test
    void incorrectDataNoMinutes(){
        //given
        String data=":06";
        //when
        long min = Task1.minutesToSeconds(data);
        //then
        assertThat(min).isEqualTo(-1);
    }
    @Test
    void incorrectDataNoSec(){
        //given
        String data="11:";
        //when
        long min = Task1.minutesToSeconds(data);
        //then
        assertThat(min).isEqualTo(-1);
    }
    @Test
    void incorrectDataNoSecAndMinutes(){
        //given
        String data=":";
        //when
        long min = Task1.minutesToSeconds(data);
        //then
        assertThat(min).isEqualTo(-1);
    }

}
