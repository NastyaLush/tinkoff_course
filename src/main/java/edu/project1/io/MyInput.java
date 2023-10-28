package edu.project1.io;

import java.util.Scanner;

public class MyInput implements Input {

    private final Scanner scanner;

    public MyInput() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String read() {
        if (scanner.hasNext()) {
            return scanner.nextLine();
        }
        return "exit";
    }
}
