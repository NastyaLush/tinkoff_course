package edu.hw2.task2.figure;

public class Builder {
    Rectangle getRectangle(int a, int b) {
        if (a == b) {
            return new Square(a);
        }
        return new Rectangle(a, b);
    }
}
