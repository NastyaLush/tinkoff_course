package edu.project1.commands;

public abstract class AbstractCommand {

    private final String name;

    protected AbstractCommand(String name) {
        this.name = name;
    }

    protected String getName() {
        return name;
    }

    protected abstract boolean execute();
}
