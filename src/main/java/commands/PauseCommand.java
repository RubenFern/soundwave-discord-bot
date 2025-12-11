package commands;

import core.Action;
import core.Commands;

public class PauseCommand extends AbstractCommand {

    public PauseCommand(Action action) {
        super(action);
    }

    @Override
    public String getName() {
        return Commands.PAUSE.getName();
    }

}