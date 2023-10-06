package edu.hw1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Task6 {
    private static final int K = 6174;
    private static final int MIN_DIGIT = 1000;
    private static final int SCALE_OF_NOTATION = 10;

    private Task6() {
    }

    public static int countK(int n) {
        if (n < 1000) {
            throw new IllegalArgumentException();
        }
        return countK(n, 0);
    }

    private static int addZerosIfDigitLess1000(int n) {
        int newN = n;
        while (newN / MIN_DIGIT == 0) {
            newN *= SCALE_OF_NOTATION;
        }
        return newN;
    }

    private static int countK(int n, int counter) {
        int ans;
        int sortedN;
        int sortedRN;
        int newN = n;

        if (newN == K) {
            return counter;
        }

        newN = addZerosIfDigitLess1000(newN);

        String strN = String.valueOf(newN);
        String sorted = Arrays
            .stream(strN.split(""))
            .sorted()
            .collect(Collectors.joining(""));
        String sortedRevers = Arrays.stream(strN.split(""))
            .sorted(Comparator.reverseOrder())
            .collect(Collectors.joining(""));

        sortedN = Integer.parseInt(sorted);
        sortedRN = Integer.parseInt(sortedRevers);
        ans = Math.abs(sortedN - sortedRN);
        if (ans == 0) {
            throw new IllegalArgumentException();
        }
        return countK(ans, counter + 1);

    }

}
