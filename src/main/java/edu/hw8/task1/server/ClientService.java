package edu.hw8.task1.server;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ClientService implements Runnable {

    private final Socket client;
    private final PrintWriter out;
    private final BufferedReader in;
    private final QuatotationService quatotationService;

    public ClientService(Socket client, QuatotationService quatotationService) throws IOException {
        log.info("create client service");
        this.client = client;
        this.out = new PrintWriter(new BufferedOutputStream(client.getOutputStream()), true);
        this.in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        this.quatotationService = quatotationService;
    }

    @Override
    public void run() {
        try {
            log.info("start read loop");
            String input;
            while ((input = in.readLine()) != null) {
                log.info("received: " + input);
                out.println(quatotationService.getQuote(input));
            }
        } catch (IOException e) {
            log.error("Error reading from client: " + e.getMessage());
        } finally {
            closeConnection();
        }
    }

    private void closeConnection() {
        try {
            in.close();
            out.close();
            client.close();
            log.info("finish reading " + client);
        } catch (IOException e) {
            log.error("Error closing connection", e);
        }
    }

}
