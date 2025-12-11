package commands;

import core.Commands;

public class SkipCommand extends AbstractCommand {

    public SkipCommand(core.Action action) {
        super(action);
    }

    @Override
    public String getName() {
        return Commands.SKIP.getName();
    }

}