package edu.hw2.task3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class DefaultConnectionManagerTest {

    @ParameterizedTest(name = "Test {index} default connection manager must return faulty connection with probability {0}")
    @ValueSource(doubles = {0.1, 0.2, 0.5, 0.7, 0.9, 1})
    public void defaultConnectionManager_shouldReturnFaultyConnectionWithProbability(double probability) {
        DefaultConnectionManager defaultConnectionManager = new DefaultConnectionManager(probability);
        Connection connection;
        do {
            connection = defaultConnectionManager.getConnection();
        }
        while (!(connection instanceof FaultyConnection));
    }

    @Test
    @DisplayName("Default connection manager must return faulty connection with default probability")
    public void defaultConnectionManager_shouldReturnFaultyConnectionWithDefaultProbability() {
        DefaultConnectionManager defaultConnectionManager = new DefaultConnectionManager();
        Connection connection;
        do {
            connection = defaultConnectionManager.getConnection();
        }
        while (!(connection instanceof FaultyConnection));
    }
}
