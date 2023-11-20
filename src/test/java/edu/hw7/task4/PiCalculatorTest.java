package edu.hw7.task4;

import org.junit.jupiter.api.Test;

public class PiCalculatorTest {

    @Test
    public void calcPi_shouldCalcPi() throws InterruptedException {
        PiCalculator piCalculator = new PiCalculator();
        piCalculator.calcPI(10000, 1, 1);
        piCalculator.calcPI(10000, 1, 2);
        piCalculator.calcPI(10000, 1, 5);
        piCalculator.calcPI(10000, 1, 10);
        piCalculator.calcPI(10000, 1, 50);
        piCalculator.calcPI(10000, 1, 100);
        piCalculator.calcPI(10000, 1, 103);
        piCalculator.calcPI(10000, 1, 1000);
        piCalculator.calcPI(10000, 1, 3000);
        piCalculator.calcPI(10000, 1, 5000);

        piCalculator.calcPI(10000000, 1, 1);
        piCalculator.calcPI(10000000, 1, 5);
        piCalculator.calcPI(10000000, 1, 10);
        piCalculator.calcPI(10000000, 1, 50);
        piCalculator.calcPI(10000000, 1, 100);
        piCalculator.calcPI(10000000, 1, 103);
        piCalculator.calcPI(10000000, 1, 1000);
        piCalculator.calcPI(10000000, 1, 2000);
    }
}
