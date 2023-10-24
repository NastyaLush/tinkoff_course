package edu.hw1;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task3Test {

    private static Stream<Arguments> isNestableTestMethodProvider() {

        return Stream.of(
            Arguments.of(new int[] {}, new int[] {}, false),
            Arguments.of(new int[] {1}, new int[] {}, false),
            Arguments.of(new int[] {}, new int[] {1}, true),
            Arguments.of(new int[] {1}, new int[] {8}, false),
            Arguments.of(new int[] {8}, new int[] {1}, false),
            Arguments.of(new int[] {8}, new int[] {8}, false),
            Arguments.of(new int[] {1, 2}, new int[] {0, 5, 3}, true),
            Arguments.of(new int[] {1, 7, 1}, new int[] {0, 5, 3}, false),
            Arguments.of(new int[] {0, 5, 3}, new int[] {0, 5, 3}, false),
            Arguments.of(new int[] {0, 4, 1}, new int[] {1, 5, 3}, false),
            Arguments.of(new int[] {0, 4, 3}, new int[] {-1, 5, 3}, true),
            Arguments.of(new int[] {-1, 4, 3}, new int[] {-2, 5, 3}, true)
        );
    }

    @ParameterizedTest(name = "Iteration #{index} -> Given array1={0}, given array2={1}, expected answer={2}")
    @MethodSource("isNestableTestMethodProvider")
    void isNestableTest(int[] arr1, int[] arr2, boolean ans) {
        assertThat(Task3.isNestable(arr1, arr2)).isEqualTo(ans);
    }

}
