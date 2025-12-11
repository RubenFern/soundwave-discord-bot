package actions;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import lavaplayer.GuildMusicManager;
import lavaplayer.PlayerManager;
import net.dv8tion.jda.api.entities.Guild;

import java.util.concurrent.TimeUnit;

public class NowPlayingAction extends AbstractAction {

    @Override
    public void action(core.CommandContext context, String[] args) {
        var event = context.getSlashEvent();

        Guild guild = getGuild(context);

        if (guild.getAudioManager().getConnectedChannel() == null) {
            event.reply("No estoy conectado a ningún canal.").setEphemeral(true).queue();
            return;
        }

        PlayerManager playerManager = PlayerManager.getInstance();
        GuildMusicManager musicManager = playerManager.getGuildMusicManager(guild.getIdLong());
        AudioTrack track = musicManager.player.getPlayingTrack();

        if (track == null) {
            event.reply("No hay música sonando en este momento.").setEphemeral(true).queue();
            return;
        }

        AudioTrackInfo info = track.getInfo();
        long current = track.getPosition();
        long total = track.getDuration();

        String progressBar = getProgressBar(current, total);
        String timeString = String.format("%s / %s", formatTime(current), formatTime(total));

        String message = String.format(
                "💿 **Sonando ahora:**\n[`%s`](%s)\n\n%s\n`%s`\n\n**Autor:** %s",
                info.title,
                info.uri,
                progressBar,
                timeString,
                info.author
        );

        event.reply(message).queue();
    }

    private String getProgressBar(long current, long total) {
        int totalBars = 20;
        int percent = (int) ((current * totalBars) / total);
        StringBuilder bar = new StringBuilder("[");

        for (int i = 0; i < totalBars; i++) {
            if (i == percent) {
                bar.append("🔘");
            } else if (i < percent) {
                bar.append("▬");
            } else {
                bar.append("➖");
            }
        }
        bar.append("]");
        return bar.toString();
    }

    private String formatTime(long millis) {
        return String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(millis),
                TimeUnit.MILLISECONDS.toMinutes(millis) % 60,
                TimeUnit.MILLISECONDS.toSeconds(millis) % 60);
    }
}