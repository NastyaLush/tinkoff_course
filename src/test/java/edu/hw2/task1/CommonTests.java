package edu.hw2.task1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CommonTests {

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
