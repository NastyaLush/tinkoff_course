package edu.hw7.task4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PiCalculatorTest {

    @Test
    @DisplayName("dont know how to test just run it")
    public void calcPi_shouldCalcPi() throws InterruptedException {
        PiCalculator piCalculator = new PiCalculator();
        piCalculator.calcPI(10000, 1);
        piCalculator.calcPI(10000, 2);
        piCalculator.calcPI(10000, 5);
        piCalculator.calcPI(10000, 10);
        piCalculator.calcPI(10000, 50);
        piCalculator.calcPI(10000, 100);
        piCalculator.calcPI(10000, 103);
        piCalculator.calcPI(10000, 1000);
        piCalculator.calcPI(10000, 3000);
        piCalculator.calcPI(10000, 5000);

        piCalculator.calcPI(10000000, 1);
        piCalculator.calcPI(10000000, 5);
        piCalculator.calcPI(10000000, 10);
        piCalculator.calcPI(10000000, 50);
        piCalculator.calcPI(10000000, 100);
        piCalculator.calcPI(10000000, 103);
        piCalculator.calcPI(10000000, 1000);
        piCalculator.calcPI(10000000, 2000);
    }
}
