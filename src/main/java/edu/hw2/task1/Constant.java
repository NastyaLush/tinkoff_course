package edu.hw2.task1;

public record Constant(Double a) implements Expr {
    @Override
    public double evaluate() {
        return a;
    }
}
