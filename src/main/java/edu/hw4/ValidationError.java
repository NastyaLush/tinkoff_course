package edu.hw4;

import lombok.Getter;

@Getter
public class ValidationError extends Exception {

    public ValidationError(TypeException type, String nameField) {
        super("validation animal error");
        this.type = type;
        this.nameField = nameField;
    }

    private final TypeException type;
    private final String nameField;
}
