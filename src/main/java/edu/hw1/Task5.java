package edu.hw1;

public class Task5 {

    private static final int SCALE_OF_NOTATION = 10;

    private Task5() {
    }

    public static boolean isPalindromeDescendant(long n) {
        String strN = String.valueOf(n);
        if (strN.length() == 1) {
            return false;
        }
        if (isPalindrome(strN)) {
            return true;
        }
        int newN = 0;
        int addition;
        for (int i = 0; i < strN.length() - 1; i += 2) {

            addition =
                (Character.getNumericValue(strN.charAt(i)) + Character.getNumericValue(strN.charAt(i + 1)));
            // на тот случай если получим двухзначное число
            if (addition / SCALE_OF_NOTATION == 0) {
                newN = newN * SCALE_OF_NOTATION + addition;
            } else {
                newN = newN * SCALE_OF_NOTATION * SCALE_OF_NOTATION + addition;
            }
        }
        if (strN.length() % 2 != 0) {
            newN = newN * SCALE_OF_NOTATION + Character.getNumericValue(strN.charAt(strN.length() - 1));
        }
        return isPalindromeDescendant(newN);
    }

    private static boolean isPalindrome(String strN) {
        int len = strN.length();
        for (int i = 0; i < len / 2; i++) {
            if (strN.charAt(i) != strN.charAt(len - 1 - i)) {
                return false;
            }
        }
        return true;
    }
}
