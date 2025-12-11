package actions;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import core.CommandContext;
import lavaplayer.GuildMusicManager;
import lavaplayer.PlayerManager;
import net.dv8tion.jda.api.entities.Guild;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class QueueAction extends AbstractAction {

    @Override
    public void action(CommandContext context, String[] args) {
        var event = context.getSlashEvent();
        Guild guild = getGuild(context);

        PlayerManager playerManager = PlayerManager.getInstance();
        GuildMusicManager musicManager = playerManager.getGuildMusicManager(guild.getIdLong());

        BlockingQueue<AudioTrack> queue = musicManager.scheduler.getQueue();
        AudioTrack currentTrack = musicManager.player.getPlayingTrack();

        if (currentTrack == null && queue.isEmpty()) {
            event.reply("📭 La cola está vacía y no suena nada.").setEphemeral(true).queue();
            return;
        }

        StringBuilder sb = new StringBuilder();

        if (currentTrack != null) {
            sb.append("💿 **Sonando ahora:**\n");
            sb.append("`").append(currentTrack.getInfo().title).append("` - ")
                    .append(formatTime(currentTrack.getDuration())).append("\n\n");
        }

        if (queue.isEmpty()) {
            sb.append("La cola está vacía.");
        } else {
            sb.append("📜 **Próximas canciones:**\n");
            int trackCount = 0;

            List<AudioTrack> trackList = new ArrayList<>(queue);

            for (AudioTrack track : trackList) {
                if (trackCount >= 10) {
                    sb.append("... y ").append(trackList.size() - 10).append(" más.");
                    break;
                }

                AudioTrackInfo info = track.getInfo();
                sb.append(trackCount + 1).append(". `")
                        .append(info.title).append("` - `")
                        .append(info.author).append("`\n");

                trackCount++;
            }

            long totalDuration = trackList.stream().mapToLong(AudioTrack::getDuration).sum();
            sb.append("\n**Duración total en cola:** ").append(formatTime(totalDuration));
        }

        event.reply(sb.toString()).queue();
    }

    private String formatTime(long millis) {
        long hours = TimeUnit.MILLISECONDS.toHours(millis);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(millis) % 60;
        long seconds = TimeUnit.MILLISECONDS.toSeconds(millis) % 60;

        if (hours > 0) {
            return String.format("%02d:%02d:%02d", hours, minutes, seconds);
        } else {
            return String.format("%02d:%02d", minutes, seconds);
        }
    }
}