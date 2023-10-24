package edu.hw2.task2.figure;

public class Rectangle {

    private final int width;
    private final int height;

    Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    double area() {
        return width * height;
    }
}
