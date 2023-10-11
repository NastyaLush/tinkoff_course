package edu.project1;

import edu.project1.commands.AbstractCommand;
import edu.project1.commands.CommandExecutor;
import edu.project1.commands.CommandManager;
import edu.project1.commands.Executor;
import edu.project1.commands.MyIllegalArgumentException;
import edu.project1.io.Input;
import edu.project1.io.Output;

public class HangMan {
    private final Dictionary dictionary;
    private final Input input;
    private final Output output;
    private final Executor executor;
    private final CommandManager commandManager;
    private final String HELLO_MESSAGE = """
        Welcome to the gallows game
        You have some options:
        help - get help information
        guess_word - guess next word
        exit - finish game
        """;
    private final String TYPE_ERROR_MESSAGE =
        "This command is not exist, type help to have information about opportunities";

    public HangMan(Dictionary dictionary, Input input, Output output, Executor executor, CommandManager commandManager) {
        this.dictionary = dictionary;
        this.input = input;
        this.output = output;
        this.executor = executor;
        this.commandManager= commandManager;
    }

    public void play() {
        boolean shouldContinueGame = true;
        AbstractCommand inputCommand;
        output.write(HELLO_MESSAGE);
        while (shouldContinueGame) {
            String userInput = input.read();
            try {
                inputCommand = commandManager.getCommand(userInput, dictionary, input, output);
                shouldContinueGame = executor.executeOperation(inputCommand);
            } catch (MyIllegalArgumentException e) {
                output.write(TYPE_ERROR_MESSAGE);
            }
        }
    }

}
