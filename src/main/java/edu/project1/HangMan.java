package edu.project1;

import edu.project1.commands.AbstractCommand;
import edu.project1.commands.CommandManager;
import edu.project1.commands.Executor;
import edu.project1.commands.WrongInputException;
import edu.project1.io.Input;
import edu.project1.io.Output;
import static edu.project1.Messages.HELLO_MESSAGE;
import static edu.project1.Messages.TYPE_ERROR_MESSAGE;

public class HangMan {

    private final Dictionary dictionary;
    private final Input input;
    private final Output output;
    private final Executor executor;
    private final CommandManager commandManager;

    public HangMan(
        Dictionary dictionary,
        Input input,
        Output output,
        Executor executor,
        CommandManager commandManager
    ) {
        this.dictionary = dictionary;
        this.input = input;
        this.output = output;
        this.executor = executor;
        this.commandManager = commandManager;
    }

    public void play() {
        boolean gameShouldBeContinued = true;
        AbstractCommand inputCommand;
        output.write(HELLO_MESSAGE);
        while (gameShouldBeContinued) {
            String userInput = input.read();
            try {
                inputCommand = commandManager.getCommand(userInput, dictionary, input, output);
                gameShouldBeContinued = executor.executeOperation(inputCommand);
            } catch (WrongInputException e) {
                output.write(TYPE_ERROR_MESSAGE);
            }
        }
    }

}
