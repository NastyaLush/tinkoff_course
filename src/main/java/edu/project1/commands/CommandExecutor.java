package edu.project1.commands;

import edu.project1.Dictionary;
import edu.project1.io.Input;
import edu.project1.io.Output;

public class CommandExecutor implements Executor {

    @Override
    public boolean executeOperation(AbstractCommand command) {
        return command.execute();
    }
}
