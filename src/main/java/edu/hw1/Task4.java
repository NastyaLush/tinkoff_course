package edu.hw1;

public class Task4 {
    private Task4() {
    }

    public static String fixString(String str) {
        char[] strChar = str.toCharArray();
        for (int i = 0; i < str.length() - 1; i += 2) {
            char temporary = strChar[i];
            strChar[i] = strChar[i + 1];
            strChar[i + 1] = temporary;
        }
        return String.valueOf(strChar);
    }
}
