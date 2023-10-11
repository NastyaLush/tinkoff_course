package edu.project1.commands;

public class Help extends AbstractCommand {
    private final String MESSAGE = """
        You have some options:
        help - get help information
        guess_word - guess next word
        exit - finish game
        """;

    protected Help() {
        super("help");
    }

    @Override
    protected boolean execute() {
        System.out.println(MESSAGE);
        return true;
    }

}
