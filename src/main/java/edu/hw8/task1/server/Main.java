package edu.hw8.task1.server;

import java.io.IOException;

public class Main {

    private static final int PORT = 4000;
    private static final int COUNT_OF_THREADS = 2;

    private Main() {}

    private static QuatotationService configureQuatotationService() {
        QuatotationService quatotationService = new QuatotationService();
        quatotationService.addQuote("I will not apologize for simply expediting the inevitable");
        quatotationService.addQuote("I don't care who makes the kill, as long as I can... observe it die");
        quatotationService.addQuote("To say your death was a waste is the understatement of the century");
        quatotationService.addQuote("Being fundamentally better, sours the taste of victory");
        quatotationService.addQuote("Maybe innocence is a skin you must shed to build layers "
                                        + "more resistant to the caustic truths of the world.");
        return quatotationService;
    }

    public static void main(String[] args) throws IOException {

        ServerSocketService.startInteractiveMode(configureQuatotationService(), PORT, COUNT_OF_THREADS);
    }
}
