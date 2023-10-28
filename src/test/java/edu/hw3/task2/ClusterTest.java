package edu.hw3.task2;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ClusterTest {

    public static Stream<Arguments> clusterizeCorrectInputProvider() {
        return Stream.of(
            Arguments.of("()()()", new ArrayList<>(List.of("()", "()", "()"))),
            Arguments.of("((()))", new ArrayList<>(List.of("((()))"))),
            Arguments.of("((()))(())()()(()())", new ArrayList<>(List.of("((()))", "(())", "()", "()", "(()())"))),
            Arguments.of("((())())(()(()()))", new ArrayList<>(List.of("((())())", "(()(()()))"))),
            Arguments.of("", new ArrayList<>())
        );
    }

    public static Stream<Arguments> clusterizeThrowExceptionProvider() {
        return Stream.of(
            Arguments.of("(("),
            Arguments.of("((())"),
            Arguments.of("((())((())()()(()())"),
            Arguments.of("((()))()()(()())(")
        );
    }

    @ParameterizedTest
    @MethodSource("clusterizeCorrectInputProvider")
    public void clusterize_shouldReturnCorrectClustersWithCorrectInput(
        String inputString, ArrayList<String> expectedArray
    ) {

        ArrayList<String> actualArray = new Cluster().clusterize(inputString);

        assertEquals(expectedArray, actualArray);
    }

    @ParameterizedTest
    @MethodSource("clusterizeThrowExceptionProvider")
    public void clusterize_shouldThrowExceptionWithIncorrectInput(String inputString) {

        assertThrows(
            IllegalArgumentException.class,
            () -> new Cluster().clusterize(inputString)
        );

    }

}
