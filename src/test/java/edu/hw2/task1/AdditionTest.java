package edu.hw2.task1;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class AdditionTest {

    private static Stream<Arguments> checkAdditionProvider() {
        return Stream.of(
            Arguments.of(new Constant(1.0), new Constant((double) 0), 1),
            Arguments.of(new Constant((double) -1), new Constant((double) 0), -1),
            Arguments.of(new Constant(8435.0), new Constant((double) 3748734), 3748734 + 8435),
            Arguments.of(new Negate(new Constant(8435.0)), new Constant((double) 3748734), -3748734 + 8435),
            Arguments.of(new Constant(8435.0), new Negate(new Constant((double) 3748734)), 3748734 - 8435)
        );
    }

    @ParameterizedTest
    @MethodSource("checkAdditionProvider")
    public void checkAddition(Expr first, Expr second, double result) {
        Addition addition = new Addition(first, second);
        assertThat(result == addition.evaluate());
    }
}
