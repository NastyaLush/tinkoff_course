package edu.project3.argument;

public enum ArgumentType {
    PATH("--path"),
    FROM("--from"),
    TO("--to"),
    FORMAT("--format");
    private final String argumentInputString;

    ArgumentType(String string) {
        this.argumentInputString = string;
    }

    public String getArgumentInputString() {
        return argumentInputString;
    }
}
