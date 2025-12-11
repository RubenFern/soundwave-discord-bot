package actions;

import core.CommandContext;
import lavaplayer.GuildMusicManager;
import lavaplayer.PlayerManager;
import net.dv8tion.jda.api.entities.Guild;

public class PauseAction extends AbstractAction {

    @Override
    public void action(CommandContext context, String[] args) {
        var event = context.getSlashEvent();

        Guild guild = getGuild(context);
        getAudioManager(context);
        getMember(context);

        PlayerManager playerManager = PlayerManager.getInstance();
        GuildMusicManager musicManager = playerManager.getGuildMusicManager(guild.getIdLong());

        if (musicManager.player.getPlayingTrack() == null) {
            event.reply("❌ No hay música sonando para pausar.").setEphemeral(true).queue();
            return;
        }

        if (musicManager.player.isPaused()) {
            event.reply("⚠️ La música ya está pausada.").setEphemeral(true).queue();
            return;
        }

        musicManager.player.setPaused(true);

        event.reply("⏸️ **Música pausada**. Usa `/continue` para continuar.").queue();
    }
}