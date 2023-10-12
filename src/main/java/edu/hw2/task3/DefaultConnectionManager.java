package edu.hw2.task3;

public class DefaultConnectionManager implements ConnectionManager {
    private final double probabilityOfFaultyConnection = 0.4;

    @Override
    public Connection getConnection() {
        if (probabilityOfFaultyConnection > Math.random()) {
            return new FaultyConnection();
        }
        return new StableConnection();
    }
}
