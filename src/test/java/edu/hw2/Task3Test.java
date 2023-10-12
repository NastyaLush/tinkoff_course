package edu.hw2;

import edu.hw2.task3.ConnectionException;
import edu.hw2.task3.DefaultConnectionManager;
import edu.hw2.task3.FaultyConnection;
import edu.hw2.task3.FaultyConnectionManager;
import edu.hw2.task3.PopularCommandExecutor;
import edu.hw2.task3.StableConnection;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task3Test {

    @Test
    public void checkThatStableConnectionAlwaysWork() {
        StableConnection stableConnection = new StableConnection();
        for (int i = 0; i < 1000; i++) {
            stableConnection.execute("dd");
        }
    }

    @Test
    public void checkThatFaultyConnectionSometimesThrowsExc() {
        FaultyConnection faultyConnection = new FaultyConnection();
        assertThrows(
            ConnectionException.class,
            () -> {
                for (int i = 0; i < 100; i++) {
                    faultyConnection.execute("dd");
                }
            }
        );
    }

    @Test
    public void checkThatFaultyConnectionSometimesWork() {
        int i = 0;
        int mistakes = 0;
        FaultyConnection faultyConnection = new FaultyConnection();
        while (i != 100) {
            try {
                faultyConnection.execute("dd");
            } catch (ConnectionException e) {
                mistakes++;
            } finally {
                i++;
            }

        }
        assertThat(i != mistakes);
    }

    @Test
    public void checkThatDefaultConnectionManagerSometimesReturnFaultyConnection() {
        DefaultConnectionManager defaultConnectionManager = new DefaultConnectionManager();

        Boolean results[] = new Boolean[1000];
        for (int i = 0; i < 1000; i++) {
            results[i] = defaultConnectionManager.getConnection().getClass() == FaultyConnection.class;
        }
        assertThat(true).isIn(results);
    }

    @Test
    public void checkThatDefaultConnectionManagerSometimesReturnStableConnection() {
        DefaultConnectionManager defaultConnectionManager = new DefaultConnectionManager();

        Boolean results[] = new Boolean[1000];
        for (int i = 0; i < 1000; i++) {
            results[i] = defaultConnectionManager.getConnection().getClass() == FaultyConnection.class;
        }
        assertThat(false).isIn(results);
    }

    @Test
    public void checkThatFaultyConnectionManagerAlwaysReturnFaultyConnection() {
        FaultyConnectionManager faultyConnectionManager = new FaultyConnectionManager();

        Boolean results[] = new Boolean[1000];
        for (int i = 0; i < 1000; i++) {
            results[i] = faultyConnectionManager.getConnection().getClass() == FaultyConnection.class;
        }
        assertFalse(Arrays.asList(results).contains(false));
    }

    @Test
    public void tryExecute_shouldThrowExceptionAfterTryingToExecuteMaxAttemptsTimes() {
        PopularCommandExecutor popularCommandExecutor = new PopularCommandExecutor(new FaultyConnectionManager(), 1);
        assertThrows(
            ConnectionException.class,
            () ->
            {
                for (int i = 0; i < 100; i++) {
                    popularCommandExecutor.tryExecute("smth");
                }
            }
        );
    }

}
