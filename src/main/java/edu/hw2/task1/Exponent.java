package edu.hw2.task1;

public record Exponent(Expr a, Double b) implements Expr {

    @Override
    public double evaluate() {
        return Math.pow(a.evaluate(), b);
    }
}
