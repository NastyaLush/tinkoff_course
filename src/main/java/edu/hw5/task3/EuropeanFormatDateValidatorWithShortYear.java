package edu.hw5.task3;

import java.time.format.DateTimeFormatter;

public class EuropeanFormatDateValidatorWithShortYear extends Validator {

    public EuropeanFormatDateValidatorWithShortYear() {
        super(DateTimeFormatter.ofPattern("d/M/yy"));
    }
}
