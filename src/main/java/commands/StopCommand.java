package commands;

import core.Action;
import core.Commands;

public class StopCommand extends AbstractCommand {

    public StopCommand(Action action) {
        super(action);
    }

    @Override
    public String getName() {
        return Commands.STOP.getName();
    }

}
