package edu.hw5.task5;

import java.util.regex.Pattern;

public class CarNumberValidator {

    private final Pattern pattern = Pattern.compile("^[ABEKMHOPCTYX]\\d{3}[ABEKMHOPCTYX]{2}\\d{2,3}$");

    public boolean isValidCarNumber(String carNumber) {
        return pattern.matcher(carNumber).matches();
    }

}
