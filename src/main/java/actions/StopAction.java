package actions;

import core.CommandContext;
import lavaplayer.GuildMusicManager;
import lavaplayer.PlayerManager;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.managers.AudioManager;

public class StopAction extends AbstractAction {

    @Override
    public void action(CommandContext context, String[] args) {
        var event = context.getSlashEvent();

        Guild guild = getGuild(context);
        AudioManager audioManager = getAudioManager(context);
        PlayerManager playerManager = PlayerManager.getInstance();
        GuildMusicManager musicManager = playerManager.getGuildMusicManager(guild.getIdLong());

        musicManager.scheduler.clearQueue();

        musicManager.player.stopTrack();

        musicManager.player.setPaused(false);

        audioManager.closeAudioConnection();

        event.reply("⏹️ Reproducción detenida y cola vaciada.").queue();
    }

}
