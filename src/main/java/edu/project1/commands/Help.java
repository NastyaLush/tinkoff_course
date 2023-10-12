package edu.project1.commands;

import edu.project1.io.Output;
import static edu.project1.Messages.HELP_MESSAGE;

public class Help extends AbstractCommand {
    private final Output output;


    protected Help(Output output) {
        super("help");
        this.output = output;
    }

    @Override
    protected boolean execute() {
        output.write(HELP_MESSAGE);
        return true;
    }

}
