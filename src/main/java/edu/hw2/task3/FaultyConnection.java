package edu.hw2.task3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FaultyConnection implements Connection {

    private static final Logger LOGGER = LogManager.getLogger();
    protected final double defaultProbabilityOfFaultyConnection = 0.4;
    protected final double probabilityOfFaultyConnection;

    public FaultyConnection() {
        this.probabilityOfFaultyConnection = this.defaultProbabilityOfFaultyConnection;
    }

    public FaultyConnection(double probabilityOfCreatingFaultyConnection) {
        this.probabilityOfFaultyConnection = probabilityOfCreatingFaultyConnection;
    }

    @Override
    public void execute(String command) {
        if (probabilityOfFaultyConnection > Math.random()) {
            throw new ConnectionException();
        }
        LOGGER.info("Execute faulty connection");
    }

    @Override
    public void close() throws Exception {
        LOGGER.info("Close stable connection");
    }
}
