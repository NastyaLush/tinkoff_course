package edu.hw7.task1;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CounterTest {
    public static Stream<Arguments> runProvider() {
        return Stream.of(
            Arguments.of(1, 10, 10),
            Arguments.of(50, 100, 50 * 100)
        
        );
    }

    @ParameterizedTest
    @MethodSource("runProvider")
    public void run_shouldStartNMethodAndSafelyIncreaseCounter(
        Integer countOfThreads,
        Integer countOfIterationsInThread,
        Integer expectedAnswer
    ) throws InterruptedException {
        Counter counter = new Counter();
        counter.run(countOfThreads, countOfIterationsInThread);

        Integer actualAnswer = counter.getCounter().get();

        assertEquals(expectedAnswer, actualAnswer);
    }
}
