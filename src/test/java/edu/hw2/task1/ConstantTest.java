package edu.hw2.task1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;

public class ConstantTest {

    @ParameterizedTest
    @ValueSource(doubles = {-1, 1, 0, 1 / 2, 1 / 3})
    public void checkConstCorrectInput(double value) {
        Constant constant = new Constant(value);
        assertThat(value == constant.evaluate());
    }
}
