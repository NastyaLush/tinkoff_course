package edu.hw2.task3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class FaultyConnectionManagerTest {

    @Test
    @DisplayName("Faulty connection manager must always return faulty connection")
    public void faultyConnectionManager_shouldReturnFaultyConnection() {
        FaultyConnectionManager faultyConnectionManager = new FaultyConnectionManager();
        Connection connection = faultyConnectionManager.getConnection();
        assertThat(connection instanceof FaultyConnection);
    }
}
