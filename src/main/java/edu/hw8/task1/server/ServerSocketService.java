package edu.hw8.task1.server;

import lombok.extern.log4j.Log4j2;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Log4j2
public class ServerSocketService {

    private final Integer port = 4000;
    private final Integer countOfThreads = 2;

    public void startInteractiveMode(QuatotationService quatotationService) throws IOException {
        log.info("start server socket");
        try (
            ExecutorService executorService = Executors.newFixedThreadPool(countOfThreads);
            ServerSocket serverSocket = new ServerSocket(port)
        ) {
            log.info("start listening clients");
            while (true) {
                Socket client = serverSocket.accept();
                log.info("receive new client: " + client);
                executorService.submit(new ClientService(client, quatotationService));
            }
        }

    }
}
