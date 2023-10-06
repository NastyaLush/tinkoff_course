package edu.hw1;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Task3Test {

    @Test
    void isNestable_shouldReturnFalseWhenReceiveEmptyArrs() {
        //given
        int[] arr2 = {};
        int[] arr1 = {};

        //when
        boolean ans = Task3.isNestable(arr1, arr2);
        //then
        assertThat(ans).isFalse();
    }

    @Test
    void isNestable_shouldReturnFalseIfSecondArrIsEmpty() {
        //given
        int[] arr2 = {};
        int[] arr1 = {1};

        //when
        boolean ans = Task3.isNestable(arr1, arr2);
        //then
        assertThat(ans).isFalse();
    }

    @Test
    void isNestable_shouldReturnTrueIfFirstArrIsEmptyNotSecond() {
        //given
        int[] arr2 = {1};
        int[] arr1 = {};

        //when
        boolean ans = Task3.isNestable(arr1, arr2);
        //then
        assertThat(ans).isTrue();
    }

    @Test
    void isNestable_shouldReturnFalseIfArrsHaveOneDigit() {
        //given
        int[] arr2 = {8};
        int[] arr1 = {1};

        //when
        boolean ans = Task3.isNestable(arr1, arr2);
        //then
        assertThat(ans).isFalse();
    }

    @Test
    void isNestable_shouldReturnFalseIfArrsHaveOneDigitRegardlessOfOrder() {
        //given
        int[] arr2 = {1};
        int[] arr1 = {8};

        //when
        boolean ans = Task3.isNestable(arr1, arr2);
        //then
        assertThat(ans).isFalse();
    }

    @Test
    void isNestable_shouldReturnFalseIfArrsHaveSameFigure() {
        //given
        int[] arr1 = {8};
        int[] arr2 = {8};

        //when
        boolean ans = Task3.isNestable(arr1, arr2);
        //then
        assertThat(ans).isFalse();
    }

    @Test
    void isNestable_shouldReturnTrueIfArr2HasTheSmallestAndTheBiggestDigit() {
        //given
        int[] arr2 = {0, 5, 3};
        int[] arr1 = {1, 2};

        //when
        boolean ans = Task3.isNestable(arr1, arr2);
        //then
        assertThat(ans).isTrue();
    }

    @Test
    void isNestable_shouldReturnFalseIfArr1HasTheBiggestDigit() {
        //given
        int[] arr2 = {0, 5, 3};
        int[] arr1 = {1, 7, 1};

        //when
        boolean ans = Task3.isNestable(arr1, arr2);
        //then
        assertThat(ans).isFalse();
    }

    @Test
    void isNestable_shouldReturnFalseIfArr1HasTheSmallestDigit() {
        //given
        int[] arr2 = {1, 5, 3};
        int[] arr1 = {0, 4, 1};

        //when
        boolean ans = Task3.isNestable(arr1, arr2);
        //then
        assertThat(ans).isFalse();
    }

    @Test
    void isNestable_shouldReturnFalseIfArrsEqual() {
        //given
        int[] arr2 = {0, 5, 3};
        int[] arr1 = {0, 5, 3};

        //when
        boolean ans = Task3.isNestable(arr1, arr2);
        //then
        assertThat(ans).isFalse();
    }

    @Test
    void isNestable_shouldWorkCorrectlyWithNegativesInSecondArr() {
        //given
        int[] arr2 = {-1, 5, 3};
        int[] arr1 = {0, 4, 3};

        //when
        boolean ans = Task3.isNestable(arr1, arr2);
        //then
        assertThat(ans).isTrue();
    }

    @Test
    void isNestable_shouldWorkCorrectlyWithNegativesInBoth() {
        //given
        int[] arr2 = {-2, 5, 3};
        int[] arr1 = {-1, 4, 3};

        //when
        boolean ans = Task3.isNestable(arr1, arr2);
        //then
        assertThat(ans).isTrue();
    }
}
