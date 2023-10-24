package edu.hw2.task1;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class ExponentTest {

    private static Stream<Arguments> checkExponentProvider() {
        return Stream.of(Arguments.of(new Constant(1.0), 0, 1), Arguments.of(new Constant((double) 0), 0, 1),
            Arguments.of(new Constant(85.0), 5.0, 85 ^ 5),
            Arguments.of(new Negate(new Constant(5.0)), -5, 5 ^ (-5)), Arguments.of(new Constant(-5.0), 5, -5 * 5),
            Arguments.of(new Constant(-5.0), 6, -5 * 6)
        );
    }

    @ParameterizedTest
    @MethodSource("checkExponentProvider")
    public void checkMultiplication(Expr first, double second, double result) {
        Exponent exponent = new Exponent(first, second);
        assertThat(result == exponent.evaluate());
    }
}
