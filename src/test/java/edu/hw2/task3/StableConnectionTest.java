package edu.hw2.task3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StableConnectionTest {

    @Test
    @DisplayName("Stable connection manager must work correctly")
    public void faultyConnectionManager_shouldReturnFaultyConnection() {
        StableConnection stableConnection = new StableConnection();
        stableConnection.execute("smth");
    }
}
