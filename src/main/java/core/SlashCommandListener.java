package core;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class SlashCommandListener extends ListenerAdapter {

    private final CommandRegistry registry;

    public SlashCommandListener(CommandRegistry registry) {
        this.registry = registry;
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        String commandName = event.getName();

        Command command = registry.getCommand(commandName);

        if (command == null) {
            return;
        }

        String[] args = new String[0];

        if (commandName.equals(Commands.PLAY.getName())) {
            String query = Objects.requireNonNull(event.getOption("query")).getAsString();
            args = new String[]{query};
        }

        command.execute(new CommandContext(event), args);
    }

}
