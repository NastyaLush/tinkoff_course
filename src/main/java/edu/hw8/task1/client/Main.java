package edu.hw8.task1.client;

import java.io.IOException;

public class Main {

    private static final int PORT = 4000;
    private static final String HOST = "localhost";

    private Main() {
    }

    public static void main(String[] args) throws IOException {

        ClientSocket.interactiveMode(HOST, PORT);
    }
}
