package commands;

import core.Action;
import core.Command;
import core.CommandContext;

public abstract class AbstractCommand implements Command {

    protected final Action action;

    protected AbstractCommand(Action action) {
        this.action = action;
    }

    @Override
    public void execute(CommandContext context, String[] args) {
        try {
            this.onAction(context, args);
        } catch (Exception e) {
            context.getSlashEvent().reply("❌ " + e.getMessage())
                    .setEphemeral(true)
                    .queue();
        }
    }

    protected void onAction(CommandContext context, String[] args) {
        this.action.action(context, args);
    }

}
