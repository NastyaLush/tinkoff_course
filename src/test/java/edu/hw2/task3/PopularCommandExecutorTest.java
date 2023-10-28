package edu.hw2.task3;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PopularCommandExecutorTest {

    @Test
    public void tryExecute_shouldThrowExceptionAfterTryingToExecuteMaxAttemptsTimes() {
        PopularCommandExecutor popularCommandExecutor = new PopularCommandExecutor(new FaultyConnectionManager(), 1);
        assertThrows(ConnectionException.class, () -> {
            while (true) {
                popularCommandExecutor.tryExecute("smth");
            }
        });
    }

    @Test
    public void tryExecute_shouldWorkCorrectlyIFReceiveStableConnection() {
        PopularCommandExecutor popularCommandExecutor = new PopularCommandExecutor(
            new DefaultConnectionManager(0),
            100
        );
        popularCommandExecutor.tryExecute("smth");
        assertThat(popularCommandExecutor.maxAttempts() == 1);
    }
}
