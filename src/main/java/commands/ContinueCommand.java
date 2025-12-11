package commands;

import core.Action;
import core.Commands;

public class ContinueCommand extends AbstractCommand {

    public ContinueCommand(Action action) {
        super(action);
    }

    @Override
    public String getName() {
        return Commands.CONTINUE.getName();
    }

}
