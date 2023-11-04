package edu.hw5.task7;

import java.util.regex.Pattern;

public class Task7 {

    public boolean lengthMoreOrEqual3AndThirdSymbol0(String input) {
        Pattern pattern = Pattern.compile("^[01]{2}0[01]*$");
        return pattern.matcher(input)
                      .find();
    }

    public boolean beginsAndEndsWithOneLetter(String input) {
        Pattern pattern = Pattern.compile("^(0|1)([01]*\\1)?$");
        return pattern.matcher(input)
                      .find();
    }

    public boolean lengthBetween1and3(String input) {
        Pattern pattern = Pattern.compile("^[01]{1,3}$");
        return pattern.matcher(input)
                      .find();
    }

}
