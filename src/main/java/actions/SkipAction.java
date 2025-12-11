package actions;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import lavaplayer.GuildMusicManager;
import lavaplayer.PlayerManager;
import net.dv8tion.jda.api.entities.Guild;

public class SkipAction extends AbstractAction {

    @Override
    public void action(core.CommandContext context, String[] args) {
        var event = context.getSlashEvent();

        Guild guild = getGuild(context);
        getAudioManager(context);
        getMember(context);

        PlayerManager playerManager = PlayerManager.getInstance();
        GuildMusicManager musicManager = playerManager.getGuildMusicManager(guild.getIdLong());

        AudioTrack currentTrack = musicManager.player.getPlayingTrack();

        if (currentTrack == null) {
            event.reply("❌ No hay ninguna canción reproduciéndose para saltar.").setEphemeral(true).queue();
            return;
        }

        musicManager.scheduler.nextTrack();

        event.reply("⏭️ Saltada: `" + currentTrack.getInfo().title + "`").queue();
    }
}