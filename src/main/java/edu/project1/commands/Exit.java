package edu.project1.commands;

public class Exit extends AbstractCommand {

    protected Exit() {
        super("exit");
    }

    @Override
    protected boolean execute() {
        return false;
    }

}
