package edu.hw1;

public class Task3 {
    public Task3() {
    }

    public static boolean isNestable(int[] arr1, int[] arr2) {
        return min(arr1) > min(arr2) && max(arr1) < max(arr2);
    }

    private static int min(int[] arr) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        return min;
    }

    private static int max(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }
}
