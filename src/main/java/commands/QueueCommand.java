package commands;

import core.Action;
import core.Commands;

public class QueueCommand extends AbstractCommand {

    public QueueCommand(Action action) {
        super(action);
    }

    @Override
    public String getName() {
        return Commands.QUEUE.getName();
    }

}
