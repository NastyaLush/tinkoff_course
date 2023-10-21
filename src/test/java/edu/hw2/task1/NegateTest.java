package edu.hw2.task1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;

public class NegateTest {

    @ParameterizedTest
    @ValueSource(doubles = {0, 1 / 2, 1 / 3, -0, +0, -4.2, -8, 5.6})
    public void checkNegate(double value) {
        Negate negate = new Negate(new Constant(value));
        assertThat(value == -negate.evaluate());
    }
}
