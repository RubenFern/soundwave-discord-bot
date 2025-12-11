package actions;

import core.Action;
import core.CommandContext;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.managers.AudioManager;

public abstract class AbstractAction implements Action {

    protected Guild getGuild(CommandContext context) {
        var event = context.getSlashEvent();
        var guild = event.getGuild();

        if (guild == null) {
            throw new IllegalStateException("Este comando solo se puede usar en un servidor.");
        }

        return guild;
    }

    protected AudioManager getAudioManager(CommandContext context) {
        var guild = getGuild(context);
        AudioManager audioManager = guild.getAudioManager();

        if (!audioManager.isConnected()) {
            throw new IllegalStateException("No estoy conectado a ningún canal de voz.");
        }

        return audioManager;
    }

    protected Member getMember(CommandContext context) {
        var event = context.getSlashEvent();
        var member = event.getMember();

        if (member == null || member.getVoiceState() == null || !member.getVoiceState().inAudioChannel()) {
            throw new IllegalStateException("Debes estar en un canal de voz para reproducir música.");
        }

        return member;
    }

}
