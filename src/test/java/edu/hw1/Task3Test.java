package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task3Test {
    @Test
    void EmptyArr(){
        //data
        int[] arr2={};
        int[] arr1={};
        //when
        boolean ans=Task3.isNestable(arr1,arr2);
        //then
        assertThat(ans).isFalse();
    }
    @Test
    void SecondArrIsEmpty(){
        //data
        int[] arr2={};
        int[] arr1={1};
        //when
        boolean ans=Task3.isNestable(arr1,arr2);
        //then
        assertThat(ans).isFalse();
    }
    @Test
    void FirstArrIsEmpty(){
        //data
        int[] arr2={1};
        int[] arr1={};
        //when
        boolean ans=Task3.isNestable(arr1,arr2);
        //then
        assertThat(ans).isTrue();
    }
    @Test
    void ArrsHasDifferentFigureArr2HasBigger(){
        //data
        int[] arr2={8};
        int[] arr1={1};
        //when
        boolean ans=Task3.isNestable(arr1,arr2);
        //then
        assertThat(ans).isFalse();
    }
    @Test
    void ArrsHasDifferentFigureArr2HasSmaller(){
        //data
        int[] arr2={1};
        int[] arr1={8};
        //when
        boolean ans=Task3.isNestable(arr1,arr2);
        //then
        assertThat(ans).isFalse();
    }
    @Test
    void ArrsHasSameFigure(){
        //data
        int[] arr1={8};
        int[] arr2={8};
        //when
        boolean ans=Task3.isNestable(arr1,arr2);
        //then
        assertThat(ans).isFalse();
    }
    @Test
    void Arr1IsNestable(){
        //data
        int[] arr2={0,5,3};
        int[] arr1={1,2};
        //when
        boolean ans=Task3.isNestable(arr1,arr2);
        //then
        assertThat(ans).isTrue();
    }
    @Test
    void Arr1IsUnnestableBecauseBigger(){
        //data
        int[] arr2={0,5,3};
        int[] arr1={1,7,1};
        //when
        boolean ans=Task3.isNestable(arr1,arr2);
        //then
        assertThat(ans).isFalse();
    }
    @Test
    void Arr1IsUnnestableBecauseSmaller(){
        //data
        int[] arr2={1,5,3};
        int[] arr1={0,4,1};
        //when
        boolean ans=Task3.isNestable(arr1,arr2);
        //then
        assertThat(ans).isFalse();
    }
    @Test
    void Arr1IsUnnestableBecauseBiggerAndSmaller(){
        //data
        int[] arr2={1,5,3};
        int[] arr1={0,4,1};
        //when
        boolean ans=Task3.isNestable(arr1,arr2);
        //then
        assertThat(ans).isFalse();
    }
    @Test
    void Arr1IsUnnestableBecauseEqual(){
        //data
        int[] arr2={0,5,3};
        int[] arr1={0,5,3};
        //when
        boolean ans=Task3.isNestable(arr1,arr2);
        //then
        assertThat(ans).isFalse();
    }
    @Test
    void Arr1IsNestableWithNegatives(){
        //data
        int[] arr2={-1,5,3};
        int[] arr1={0,4,3};
        //when
        boolean ans=Task3.isNestable(arr1,arr2);
        //then
        assertThat(ans).isTrue();
    }
    @Test
    void Arr1IsNestableWithNegativesInBoth(){
        //data
        int[] arr2={-2,5,3};
        int[] arr1={-1,4,3};
        //when
        boolean ans=Task3.isNestable(arr1,arr2);
        //then
        assertThat(ans).isTrue();
    }
}
