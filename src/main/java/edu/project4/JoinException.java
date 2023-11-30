package edu.project4;

public class JoinException extends RuntimeException {

    private final InterruptedException interruptedException;

    public JoinException(InterruptedException interruptedException) {this.interruptedException = interruptedException;}
}
