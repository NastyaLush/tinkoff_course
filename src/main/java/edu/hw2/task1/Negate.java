package edu.hw2.task1;

public record Negate(Constant a) implements Expr {
    @Override
    public double evaluate() {
        return -a.evaluate();
    }
}
