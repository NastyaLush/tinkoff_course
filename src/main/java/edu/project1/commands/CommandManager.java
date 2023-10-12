package edu.project1.commands;

import edu.project1.Dictionary;
import edu.project1.io.Input;
import edu.project1.io.Output;

public class CommandManager {
    public AbstractCommand getCommand(String command, Dictionary dictionary, Input input, Output output)
        throws MyIllegalArgumentException {
        return switch (command) {
            case "help" -> new Help(output);
            case "exit" -> new Exit();
            case "guess_word" -> new GuessWord(dictionary, input, output);
            default -> throw new MyIllegalArgumentException("this command is not exist");
        };

    }
}
