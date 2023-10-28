package edu.hw4.task19;

import lombok.Data;

@Data
public class ValidationError extends Exception {

    private final TypeException type;
    private final String nameField;

    public ValidationError(TypeException type, String nameField) {
        super("validation animal error " + type + " " + nameField);
        this.type = type;
        this.nameField = nameField;
    }
}
