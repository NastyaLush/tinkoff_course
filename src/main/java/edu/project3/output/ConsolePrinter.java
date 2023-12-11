package edu.project3.output;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class ConsolePrinter implements Printer {

    @Override
    @SuppressWarnings("RegexpSinglelineJava")
    public void print(String string) {
        System.out.print(string);
    }
}
