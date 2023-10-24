package edu.hw2.task3;

public class DefaultConnectionManager implements ConnectionManager {

    protected final double defaultProbabilityOfFaultyConnection = 0.4;
    protected final double probabilityOfFaultyConnection;

    public DefaultConnectionManager() {
        this.probabilityOfFaultyConnection = this.defaultProbabilityOfFaultyConnection;
    }

    public DefaultConnectionManager(double probability) {
        this.probabilityOfFaultyConnection = probability;
    }

    @Override
    public Connection getConnection() {
        if (probabilityOfFaultyConnection > Math.random()) {
            return new FaultyConnection();
        }
        return new StableConnection();
    }

    public Connection getCustomConnection(double probabilityOfCreatingFaultyConnection) {
        if (probabilityOfFaultyConnection > Math.random()) {
            return new FaultyConnection(probabilityOfCreatingFaultyConnection);
        }
        return new StableConnection();
    }

}
