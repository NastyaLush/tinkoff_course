package edu.hw2;

import edu.hw2.task1.Addition;
import edu.hw2.task1.Constant;
import edu.hw2.task1.Exponent;
import edu.hw2.task1.Expr;
import edu.hw2.task1.Multiplication;
import edu.hw2.task1.Negate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;

public class Task1Test {

    private static Stream<Arguments> checkAdditionProvider() {
        return Stream.of(
            Arguments.of(new Constant(1.0), new Constant((double) 0), 1 + 0),
            Arguments.of(new Constant((double) -1), new Constant((double) 0), -1),
            Arguments.of(new Constant(8435.0), new Constant((double) 3748734), 3748734 + 8435),
            Arguments.of(new Negate(new Constant(8435.0)), new Constant((double) 3748734), -3748734 + 8435),
            Arguments.of(new Constant(8435.0), new Negate(new Constant((double) 3748734)), 3748734 - 8435)
        );
    }

    private static Stream<Arguments> checkMultiplicationProvider() {
        return Stream.of(
            Arguments.of(new Constant(1.0), new Constant((double) 0), 1 * 0),
            Arguments.of(new Constant((double) -1), new Constant((double) 0), 0),
            Arguments.of(new Constant(8435.0), new Constant((double) 3748734), 3748734 * 8435),
            Arguments.of(new Negate(new Constant(8435.0)), new Constant((double) 3748734), -3748734 * 8435),
            Arguments.of(new Constant(8435.0), new Negate(new Constant((double) 3748734)), -3748734 * 8435)
        );
    }

    private static Stream<Arguments> checkExponentProvider() {
        return Stream.of(
            Arguments.of(new Constant(1.0), 0, 1),
            Arguments.of(new Constant((double) 0), 0, 1),
            Arguments.of(new Constant(85.0), 5.0, 85 ^ 5),
            Arguments.of(new Negate(new Constant(5.0)), -5, 5 ^ (-5)),
            Arguments.of(new Constant(-5.0), 5, -5 * 5),
            Arguments.of(new Constant(-5.0), 6, -5 * 6)
        );
    }

    @Test
    @DisplayName("Проверка теста из примера")
    public void exampleTest() {
        var two = new Constant(2.0);
        var four = new Constant(4.0);
        var negOne = new Negate(new Constant(1.0));
        var sumTwoFour = new Addition(two, four);
        var mult = new Multiplication(sumTwoFour, negOne);
        var exp = new Exponent(mult, 2.0);
        var res = new Addition(exp, new Constant(1.0));

        assertThat((((2 + 4) * (-1)) ^ 2 + 1) == res.evaluate());
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1, 1, 0, 1 / 2, 1 / 3})
    public void checkConstCorrectInput(double value) {
        Constant constant = new Constant(value);
        assertThat(value == constant.evaluate());
    }

    @ParameterizedTest
    @ValueSource(doubles = {0, 1 / 2, 1 / 3, -0, +0, -4.2, -8, 5.6})
    public void checkNegate(double value) {
        Negate negate = new Negate(new Constant(value));
        assertThat(value == -negate.evaluate());
    }

    @ParameterizedTest
    @MethodSource("checkAdditionProvider")
    public void checkAddition(Expr first, Expr second, double result) {
        Addition addition = new Addition(first, second);
        assertThat(result == addition.evaluate());
    }

    @ParameterizedTest
    @MethodSource("checkMultiplicationProvider")
    public void checkMultiplication(Expr first, Expr second, double result) {
        Multiplication multiplication = new Multiplication(first, second);
        assertThat(result == multiplication.evaluate());
    }

    @ParameterizedTest
    @MethodSource("checkExponentProvider")
    public void checkMultiplication(Expr first, double second, double result) {
        Exponent exponent = new Exponent(first, second);
        assertThat(result == exponent.evaluate());
    }

    @Test
    @DisplayName("проверка операций путем вычисления факториала")
    public void checkFactorial() {
        assertThat(factorial(new Constant(12.0)).evaluate() == 479001600.0);
    }

    private Expr factorial(Expr a) {
        if (a.evaluate() == 1) {
            return new Constant(1.0);
        }
        return new Multiplication(a, new Constant(new Addition(a, new Negate(new Constant(1.0))).evaluate()));
    }
}
