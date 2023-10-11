package edu.project1.io;

public class MyOutput implements Output {
    @Override
    public void write(String string) {
        System.out.println(string);
    }
}
