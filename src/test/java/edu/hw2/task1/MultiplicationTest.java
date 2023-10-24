package edu.hw2.task1;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class MultiplicationTest {

    private static Stream<Arguments> checkMultiplicationProvider() {
        return Stream.of(
            Arguments.of(new Constant(1.0), new Constant((double) 0), 0),
            Arguments.of(new Constant((double) -1), new Constant((double) 0), 0),
            Arguments.of(new Constant(8435.0), new Constant((double) 3748734), 3748734 * 8435),
            Arguments.of(new Negate(new Constant(8435.0)), new Constant((double) 3748734), -3748734 * 8435),
            Arguments.of(new Constant(8435.0), new Negate(new Constant((double) 3748734)), -3748734 * 8435)
        );
    }

    @ParameterizedTest
    @MethodSource("checkMultiplicationProvider")
    public void checkMultiplication(Expr first, Expr second, double result) {
        Multiplication multiplication = new Multiplication(first, second);
        assertThat(result == multiplication.evaluate());
    }
}
