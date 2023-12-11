package edu.hw7.task2;

import java.util.stream.LongStream;

public class ParallelFactorial {

    public Long calcFactorial(Integer n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        if (n == 1) {
            return 1L;
        }
        return LongStream.range(1, n).parallel().reduce((first, second) -> first * second).getAsLong();
    }
}
