package edu.hw2.task3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FaultyConnection implements Connection{
    private static final Logger LOGGER = LogManager.getLogger();
    @Override
    public void execute(String command) {
        if(Math.random()>0.4) throw  new ConnectionException();
        LOGGER.info("Execute faulty connection");
    }

    @Override
    public void close() throws Exception {
        LOGGER.info("Close stable connection");
    }
}
