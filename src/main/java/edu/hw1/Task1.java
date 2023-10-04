package edu.hw1;

import java.util.regex.Pattern;

public class Task1 {
    public static long minutesToSeconds(String str) {
        if (Pattern.matches("\\d{2,}:\\d{2}", str)) {
            String[] spl_str = str.split(":");
            if(Long.parseLong(spl_str[1])>60) return -1;
            return Long.parseLong(spl_str[0]) * 60 + Long.parseLong(spl_str[1]);
        } else {
            return -1;
        }
    }
}
