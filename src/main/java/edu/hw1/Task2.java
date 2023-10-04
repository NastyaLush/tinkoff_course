package edu.hw1;

public class Task2 {
    public static int countDigits(long n) {
        int counter = 0;
        n=Math.abs(n);
        if(n==0) return 1;
        while (n > 0) {
            counter++;
            n /= 10;
        }
        return counter;
    }
}
