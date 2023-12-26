package edu.hw8.task2;

import java.util.stream.Stream;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Log4j2
public class FixedThreadPollTest {

    public static Stream<Arguments> fibonachiProvider() {
        return Stream.of(
            Arguments.of(1, 5),
            Arguments.of(1, 1),
            Arguments.of(5, 1),
            Arguments.of(5, 5),
            Arguments.of(5, 20),
            Arguments.of(100, 100),
            Arguments.of(100, 1000)
        );
    }

    @ParameterizedTest
    @MethodSource("fibonachiProvider")
    public void fibonachi(Integer countOfThreads, Integer countOfTasks) throws Exception {
        try (FixedThreadPoolImpl fixedThreadPool = new FixedThreadPoolImpl(countOfThreads)) {
            for (int i = 0; i < countOfTasks; i++) {
                fixedThreadPool.execute(() -> calcFib(getRandom()));
            }
            fixedThreadPool.start();
            for (int i = 0; i < countOfTasks; i++) {
                fixedThreadPool.execute(() -> calcFib(getRandom()));
            }
        }
    }

    private void calcFib(int n) {
        int sum = 0;
        for (int i = 0; i <= n; i++) {
            sum += i;
        }
        System.out.println("fib n: " + sum);
        log.info("fib n: " + sum);
    }

    private int getRandom() {
        return (int) (Math.random() * 1000);
    }
}
