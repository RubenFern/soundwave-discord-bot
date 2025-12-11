package commands;

import actions.Action;

public abstract class AbstractCommand implements Command {

    protected final Action action;

    protected AbstractCommand(Action action) {
        this.action = action;
    }

}
