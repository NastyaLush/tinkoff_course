package edu.hw5.task3;

import java.time.format.DateTimeFormatter;

public class USAFormatDateValidator extends Validator {

    public USAFormatDateValidator() {
        super(DateTimeFormatter.ofPattern("yyyy-MM-d"));
    }

}
