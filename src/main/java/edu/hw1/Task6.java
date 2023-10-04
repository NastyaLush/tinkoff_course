package edu.hw1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Task6 {
    public Task6() {
    }

    private static final int K = 6174;
    private static final int MIN_DIGIT = 1000;


    public static int countK(int n) {
        return countK(n, 0);
    }

    private static int addZerosIfDigitLess1000(int n){
        while (n / MIN_DIGIT == 0) {
            n *= 10;
        }
        return n;
    }
    private static int countK(int n, int counter) {
        int ans;
        int sortedN;
        int sortedRN;
        int newN=n;

        if (newN == K) {
            return counter;
        }

        newN=addZerosIfDigitLess1000(newN);

        String strN = String.valueOf(newN);
        String sorted = Arrays.stream(strN.split("")).sorted().collect(Collectors.joining(""));
        String sortedRevers = Arrays.stream(strN.split(""))
            .sorted(Comparator.reverseOrder())
            .collect(Collectors.joining(""));

        sortedN = Integer.parseInt(sorted);
        sortedRN = Integer.parseInt(sortedRevers);
        ans = Math.abs(sortedN - sortedRN);
        return countK(ans, counter + 1);

    }

}
