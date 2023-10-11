package edu.project1.commands;

import edu.project1.Dictionary;
import edu.project1.io.Input;
import edu.project1.io.Output;

public class CommandManager {
    public AbstractCommand getCommand(String command, Dictionary dictionary, Input input, Output output)
        throws MyIllegalArgumentException {
        switch (command) {
            case "help":
                return new Help();
            case "exit":
                return new Exit();
            case "guess_word":
                return new GuessWord(dictionary, input, output);
            default:
                throw new MyIllegalArgumentException("this command is not exist");
        }

    }
}
