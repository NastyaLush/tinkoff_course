package edu.hw1;

import java.util.regex.Pattern;

public class Task1 {
    private static final int COUNT_SEC_IN_MINUTES = 60;
    private static final int MAX_SEC = 60;

    public Task1() {
    }

    public static long minutesToSeconds(String str) {
        if (Pattern.matches("\\d{2,}:\\d{2}", str)) {
            String[] splStr = str.split(":");
            if (Long.parseLong(splStr[1]) > MAX_SEC) {
                return -1;
            }
            return Long.parseLong(splStr[0]) * COUNT_SEC_IN_MINUTES + Long.parseLong(splStr[1]);
        } else {
            return -1;
        }
    }
}
