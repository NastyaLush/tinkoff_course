package edu.hw2.task1;

public record Multiplication(Constant a, Constant b) implements Expr {
    @Override
    public double evaluate() {
        return a.evaluate()*b.evaluate();
    }
}
