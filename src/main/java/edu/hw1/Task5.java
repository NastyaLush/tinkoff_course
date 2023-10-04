package edu.hw1;

public class Task5 {
    public static boolean isPalindromeDescendant(long n) {
        String strN = String.valueOf(n);
        if (strN.length() == 1) {
            return false;
        }
        if (isPalindrome(strN)) {
            return true;
        }
        int newN = 0, addition = 0;
        for (int i = 0; i < strN.length()-1; i+=2) {
            // TODO: 10/4/2023
            addition =
                (Character.getNumericValue(strN.charAt(i)) + Character.getNumericValue(strN.charAt(i + 1)));

            if (addition / 10 == 0) {
                newN = newN * 10 + addition;
            } else {
                newN = newN * 100 + addition;
            }
        }
        if (strN.length() % 2 != 0) {
            newN = newN * 10 + Character.getNumericValue(strN.charAt(strN.length() - 1));
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
