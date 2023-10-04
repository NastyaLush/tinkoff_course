package edu.hw1;

public class Task2 {
    private static final int SCALE_OF_NOTATION = 10;
    public Task2() {
    }

    public static int countDigits(long n) {
        int counter = 0;
        long newN = Math.abs(n);
        if (newN == 0) {
            return 1;
        }
        while (newN > 0) {
            counter++;
            newN /= SCALE_OF_NOTATION;
        }
        return counter;
    }
}
