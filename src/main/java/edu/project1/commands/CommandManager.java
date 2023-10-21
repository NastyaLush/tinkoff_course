package edu.project1.commands;

import edu.project1.Dictionary;
import edu.project1.io.Input;
import edu.project1.io.Output;
import static edu.project1.Messages.TYPE_ERROR_MESSAGE;

public class CommandManager {

    public AbstractCommand getCommand(String command, Dictionary dictionary, Input input, Output output)
        throws WrongInputException {
        if (command == null) {
            throw new WrongInputException(TYPE_ERROR_MESSAGE);
        }
        return switch (command) {
            case "help" -> new Help(output);
            case "exit" -> new Exit();
            case "guess_word" -> new GuessWord(dictionary, input, output);
            default -> throw new WrongInputException(TYPE_ERROR_MESSAGE);
        };

    }
}
