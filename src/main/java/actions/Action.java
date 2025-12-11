package actions;

import commands.CommandContext;

public interface Action {

    void action(CommandContext context, String[] args);

}
