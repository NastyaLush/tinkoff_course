package edu.hw5.task4;

import java.util.regex.Pattern;

public class PasswordValidator {

    private final Pattern pattern = Pattern.compile("^[^~!@#$%^&*|]*[~!@#$%^&*|][^~!@#$%^&*|]*$");

    public boolean isValidPassword(String password) {
        return pattern.matcher(password)
                      .find();
    }
}
