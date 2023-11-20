package edu.hw7.task2;

import java.util.stream.LongStream;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParallelFactorialTest {
    public static Stream<Arguments> factorialProvider() {
        return Stream.of(
            Arguments.of(1, 1L),
            Arguments.of(7, LongStream.range(1, 7).reduce((first, second) -> first * second).getAsLong()),
            Arguments.of(20, LongStream.range(1, 20).reduce((first, second) -> first * second).getAsLong())
        );
    }

    @ParameterizedTest
    @MethodSource("factorialProvider")
    public void calcFactorial_shouldCorrectlyCalcFactorial(Integer givenNumber, Long expectedAnswer) {
        ParallelFactorial parallelFactorial = new ParallelFactorial();

        Long actualAnswer = parallelFactorial.calcFactorial(givenNumber);

        assertEquals(expectedAnswer, actualAnswer);
    }
}
