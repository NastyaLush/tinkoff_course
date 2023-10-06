package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task8Test {

    @Test
    void knightBoardCapture_shouldCorrectlyWorkWithExampleTest1() {
        //given
        int[][] data = {
            {0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 1, 0, 0, 0}
        };

        //when
        boolean ans = Task8.knightBoardCapture(data);
        //then
        assertThat(ans).isTrue();
    }

    @Test
    void knightBoardCapture_shouldCorrectlyWorkWithExampleTest2() {
        //given
        int[][] data = new int[][] {
            {1, 0, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 1, 0, 1},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 1, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 0, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 0, 1, 0, 1, 0, 1}
        };

        //when
        boolean ans = Task8.knightBoardCapture(data);
        //then
        assertThat(ans).isFalse();
    }

    @Test
    void knightBoardCapture_shouldCorrectlyWorkWithExampleTest3() {
        int[][] data = new int[][] {
            {0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {1, 0, 0, 0, 0, 0, 0, 0}
        };

        //when
        boolean ans = Task8.knightBoardCapture(data);
        //then
        assertThat(ans).isFalse();
    }

    @ParameterizedTest(name = "Iteration #{index} -> Given number = {0} and must receive {1}")
    @CsvSource({
        "{\n" +
            "            {1, 1, 1, 1, 1, 1, 1, 1},\n" +
            "            {1, 1, 1, 1, 1, 1, 1, 1},\n" +
            "            {1, 1, 1, 1, 1, 1, 1, 1},\n" +
            "            {1, 1, 1, 1, 1, 1, 1, 1},\n" +
            "            {1, 1, 1, 1, 1, 1, 1, 1},\n" +
            "            {1, 1, 1, 1, 1, 1, 1, 1},\n" +
            "            {1, 1, 1, 1, 1, 1, 1, 1},\n" +
            "            {1, 1, 1, 1, 1, 1, 1, 1}\n" +
            "        }, false"

    })
    void knightBoardCapture_shouldCorrectlyWorkWith1() {
        //given
        int[][] data = {
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1}
        };

        //when
        boolean ans = Task8.knightBoardCapture(data);
        //then
        assertThat(ans).isFalse();
    }

    @Test
    void knightBoardCapture_shouldCorrectlyWorkWith0() {
        //given
        int[][] data = {
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}
        };

        //when
        boolean ans = Task8.knightBoardCapture(data);
        //then
        assertThat(ans).isTrue();
    }

    @Test
    @DisplayName("Строки единиц через 2 строки нулей")
    void knightBoardCapture_shouldCorrectlyWithZerosBellowAndOnesLeftAndRight() {
        //given
        int[][] data = {
            {1, 1, 1, 1, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 0, 0, 0}
        };

        //when
        boolean ans = Task8.knightBoardCapture(data);
        //then
        assertThat(ans).isTrue();
    }

    @Test
    @DisplayName("проверка коня в левом верхнем углу")
    void knightBoardCapture_shouldCorrectlyWorkWithKnightInTheLeftUpCorner() {
        //given
        int[][] data = {
            {1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}
        };

        //when
        boolean ans = Task8.knightBoardCapture(data);
        //then
        assertThat(ans).isTrue();

        //given
        data = new int[][] {
            {1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}
        };

        //when
        ans = Task8.knightBoardCapture(data);
        //then
        assertThat(ans).isFalse();

        //given
        data = new int[][] {
            {1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}
        };

        //when
        ans = Task8.knightBoardCapture(data);
        //then
        assertThat(ans).isFalse();

        //given
        data = new int[][] {
            {1, 0, 0, 1, 0, 1, 0, 1},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}
        };

        //when
        ans = Task8.knightBoardCapture(data);
        //then
        assertThat(ans).isTrue();
    }

    @Test
    @DisplayName("проверка коня в правом верхнем углу")
    void knightBoardCapture_shouldCorrectlyWorkWithKnightInTheRightUpCorner() {
        //given
        int[][] data = {
            {0, 0, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}
        };

        //when
        boolean ans = Task8.knightBoardCapture(data);
        //then
        assertThat(ans).isTrue();

        //given
        data = new int[][] {
            {0, 0, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}
        };

        //when
        ans = Task8.knightBoardCapture(data);
        //then
        assertThat(ans).isFalse();

        //given
        data = new int[][] {
            {0, 0, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}
        };

        //when
        ans = Task8.knightBoardCapture(data);
        //then
        assertThat(ans).isFalse();

        //given
        data = new int[][] {
            {0, 0, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 0, 0, 1, 1},
            {0, 0, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}
        };

        //when
        ans = Task8.knightBoardCapture(data);
        //then
        assertThat(ans).isTrue();
    }

    @Test
    @DisplayName("проверка коня в середине")
    void knightBoardCapture_shouldCorrectlyWorkWithKnightInTheMiddle() {
        //given
        int[][] data = {
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}
        };

        //when
        boolean ans = Task8.knightBoardCapture(data);
        //then
        assertThat(ans).isFalse();

        //given
        data = new int[][] {
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}
        };

        //when
        ans = Task8.knightBoardCapture(data);
        //then
        assertThat(ans).isFalse();

        //given
        data = new int[][] {
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}
        };

        //when
        ans = Task8.knightBoardCapture(data);
        //then
        assertThat(ans).isFalse();

        //given
        data = new int[][] {
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}
        };

        //when
        ans = Task8.knightBoardCapture(data);
        //then
        assertThat(ans).isFalse();

        //given
        data = new int[][] {
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 0, 1, 0, 1, 0},
            {0, 0, 0, 1, 0, 1, 0, 0},
            {0, 0, 1, 0, 1, 0, 1, 0},
            {0, 0, 0, 1, 0, 1, 0, 0},
            {0, 0, 1, 0, 1, 0, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}
        };

        //when
        ans = Task8.knightBoardCapture(data);
        //then
        assertThat(ans).isTrue();

    }
}
