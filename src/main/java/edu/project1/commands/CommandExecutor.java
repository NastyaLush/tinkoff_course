package edu.project1.commands;

public class CommandExecutor implements Executor {

    @Override
    public boolean executeOperation(AbstractCommand command) {
        return command.execute();
    }
}
