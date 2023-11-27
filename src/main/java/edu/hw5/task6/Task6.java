package edu.hw5.task6;

import java.util.regex.Pattern;

public class Task6 {

    public boolean isSubstring(String main, String potentialSubstring) {
        Pattern pattern = Pattern.compile(".*" + Pattern.quote(potentialSubstring) + ".*");
        return pattern.matcher(main).matches();
    }

}
