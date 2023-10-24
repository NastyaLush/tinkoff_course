package edu.hw2.task3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public record PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {

    private static final Logger LOGGER = LogManager.getLogger();

    void updatePackages() {
        tryExecute("apt update && apt upgrade -y");
    }

    public void tryExecute(String command) {

        for (int i = 0; i < maxAttempts; i++) {
            try (Connection connection = manager.getConnection()) {
                connection.execute(command);
                LOGGER.info("Successfully execute " + command);
                return;
            } catch (Exception e) {
                LOGGER.warn("Attempt " + i + " Failed");
                LOGGER.warn(e.getCause());
            }
        }
        throw new ConnectionException();
    }
}
