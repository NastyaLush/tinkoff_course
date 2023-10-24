package edu.hw2.task3;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class FaultyConnectionTest {

    @ParameterizedTest(name = "Test {index} faulty connection must throw exception with probability {0}")
    @ValueSource(doubles = {0.1, 0.2, 0.5, 0.7, 0.9, 1})
    public void faultyConnection_shouldThrowErrorWithProbability(double probability) {
        try (FaultyConnection faultyConnection = new FaultyConnection(probability)) {
            while (true) {
                try {
                    faultyConnection.execute("Smth");
                } catch (ConnectionException e) {
                    break;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @ParameterizedTest(name = "Faulty connection must throw exception with default probability")
    @ValueSource(doubles = {0.1, 0.2, 0.5, 0.7, 0.9, 1})
    public void faultyConnection_shouldThrowErrorWithDefaultProbability() {
        try (FaultyConnection faultyConnection = new FaultyConnection()) {
            while (true) {
                try {
                    faultyConnection.execute("Smth");
                } catch (ConnectionException e) {
                    break;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
