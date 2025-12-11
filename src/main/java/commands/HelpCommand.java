package commands;

import core.Action;
import core.Commands;

public class HelpCommand extends AbstractCommand {

    public HelpCommand(Action action) {
        super(action);
    }

    @Override
    public String getName() {
        return Commands.HELP.getName();
    }

}
