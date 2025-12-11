package commands;

import actions.Action;

public class PlayCommand extends AbstractCommand {

    public PlayCommand(Action action) {
        super(action);
    }

    @Override
    public String getName() {
        return Commands.PLAY.getName();
    }

    @Override
    public void execute(CommandContext context, String[] args) {
        this.action.action(context, args);
    }

}
