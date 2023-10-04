package edu.hw1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Task6 {
    private static final int K = 6174;

    public static int countK(int n) {
        return countK(n, 0);
    }

    private static int countK(int n, int counter) {
        if (n == K) {
            return counter;
        }
        while (n / 1000 == 0) {
            n *= 10;
        }
        String strN = String.valueOf(n);
        int ans, sortedN, sortedRN;

        String sorted = Arrays.stream(strN.split("")).sorted().collect(Collectors.joining(""));
        String sortedRevers = Arrays.stream(strN.split("")).sorted(Comparator.reverseOrder()).collect(Collectors.joining(""));

        sortedN = Integer.parseInt(sorted);
        sortedRN = Integer.parseInt(sortedRevers);
        ans = Math.abs(sortedN - sortedRN);
        return countK(ans, counter + 1);

    }

}
