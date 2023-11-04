package edu.hw5.task3;

import java.time.format.DateTimeFormatter;

public class EuropeanFormatDateValidator extends Validator {

    public EuropeanFormatDateValidator() {
        super(DateTimeFormatter.ofPattern("d/M/yyyy"));
    }
}
