package edu.hw8.task1.client;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ClientSocket {

    private ClientSocket() {
    }

    @SuppressWarnings("RegexpSinglelineJava")
    public static void interactiveMode(String host, Integer port) throws IOException {
        try (Socket socket = new Socket(host, port);
             PrintWriter outServer = new PrintWriter(new BufferedOutputStream(socket.getOutputStream()), true);
             BufferedReader inServer = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            Scanner in = new Scanner(System.in);
            String input;
            String output;
            log.info("start working");
            while (in.hasNextLine()) {
                input = in.nextLine();
                log.info("send " + input + " to the server");

                outServer.println(input);
                log.info("wait answer from server");

                output = inServer.readLine();

                System.out.println(output);
            }
        } catch (ConnectException e) {
            log.error("impossible to connect to the server");
        } finally {
            log.info("client closed");
        }
    }
}
