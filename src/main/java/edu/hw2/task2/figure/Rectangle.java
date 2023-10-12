package edu.hw2.task2.figure;

public class Rectangle {
    private int width;
    private int height;

    Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    double area() {
        return width * height;
    }
}
