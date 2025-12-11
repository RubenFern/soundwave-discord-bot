package actions;

import core.CommandContext;
import lavaplayer.GuildMusicManager;
import lavaplayer.PlayerManager;
import net.dv8tion.jda.api.entities.Guild;

public class ContinueAction extends AbstractAction {

    @Override
    public void action(CommandContext context, String[] args) {
        var event = context.getSlashEvent();

        Guild guild = getGuild(context);
        getAudioManager(context);
        getMember(context);

        PlayerManager playerManager = PlayerManager.getInstance();
        GuildMusicManager musicManager = playerManager.getGuildMusicManager(guild.getIdLong());

        if (musicManager.player.getPlayingTrack() == null) {
            event.reply("❌ No hay nada en la cola para reanudar.").setEphemeral(true).queue();
            return;
        }

        if (!musicManager.player.isPaused()) {
            event.reply("⚠️ La música no está pausada, ya se está reproduciendo.").setEphemeral(true).queue();
            return;
        }

        musicManager.player.setPaused(false);

        event.reply("▶️ **Reanudando reproducción**.").queue();
    }

}
