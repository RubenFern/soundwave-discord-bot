package lavaplayer;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class TrackLoadCallback implements AudioLoadResultHandler {

    private final SlashCommandInteractionEvent event;
    private final GuildMusicManager musicManager;
    private final String trackIdentifier;
    private final String query;

    public TrackLoadCallback(SlashCommandInteractionEvent event,
                             GuildMusicManager musicManager,
                             String trackIdentifier,
                             String query) {
        this.event = event;
        this.musicManager = musicManager;
        this.trackIdentifier = trackIdentifier;
        this.query = query;
    }

    @Override
    public void loadFailed(FriendlyException exception) {
        event.getHook().sendMessage("❌ No se ha podido cargar la pista: `" + exception.getMessage() + "`").queue();
    }

    /**
     * Called when a single track has been loaded.
     */
    @Override
    public void trackLoaded(AudioTrack track) {
        musicManager.scheduler.queue(track);
        event.getHook().sendMessage("▶️ Añadido: `" + track.getInfo().title + "`").queue();
    }

    /**
     * Called when a playlist has been loaded.
     */
    @Override
    public void playlistLoaded(AudioPlaylist playlist) {
        if (trackIdentifier.startsWith("ytsearch:")) {
            var track = playlist.getTracks().getFirst();
            musicManager.scheduler.queue(track);
            event.getHook().sendMessage("▶️ Reproduciendo: `" + track.getInfo().title + "`").queue();
        } else {
            for (var track : playlist.getTracks()) {
                musicManager.scheduler.queue(track);
            }
            event.getHook().sendMessage("📃 Playlist añadida: `" + playlist.getName()
                    + "` (" + playlist.getTracks().size() + " temas)").queue();
        }
    }

    @Override
    public void noMatches() {
        event.getHook().sendMessage("No he encontrado resultados para: `" + query + "`.").queue();
    }
}
