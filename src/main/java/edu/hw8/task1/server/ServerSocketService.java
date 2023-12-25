package edu.hw8.task1.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ServerSocketService {

    private ServerSocketService() {
    }

    public static void startInteractiveMode(QuatotationService quatotationService, Integer port, Integer countOfThreads)
        throws IOException {
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
