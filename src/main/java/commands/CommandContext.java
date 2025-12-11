package commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class CommandContext {

    private final SlashCommandInteractionEvent slashCommandInteractionEvent;
    
    public CommandContext(SlashCommandInteractionEvent slashEvent) {
        this.slashCommandInteractionEvent = slashEvent;
    }

    public SlashCommandInteractionEvent getSlashEvent() {
        return slashCommandInteractionEvent;
    }
    
}
