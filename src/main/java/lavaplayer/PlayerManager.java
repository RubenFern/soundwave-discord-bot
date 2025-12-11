package lavaplayer;

import com.sedmelluq.discord.lavaplayer.player.AudioConfiguration;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.track.playback.NonAllocatingAudioFrameBuffer;
import dev.lavalink.youtube.YoutubeAudioSourceManager;

import java.util.HashMap;
import java.util.Map;

public class PlayerManager {
    private static PlayerManager INSTANCE;

    private final AudioPlayerManager playerManager;
    private final Map<Long, GuildMusicManager> musicManagers = new HashMap<>();

    private PlayerManager() {
        this.playerManager = new DefaultAudioPlayerManager();

        // Configuración opcional de calidad/buffer
        playerManager.getConfiguration().setFrameBufferFactory(NonAllocatingAudioFrameBuffer::new);
        playerManager.getConfiguration().setResamplingQuality(AudioConfiguration.ResamplingQuality.MEDIUM);

        playerManager.registerSourceManager(new YoutubeAudioSourceManager());
    }

    public static synchronized PlayerManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PlayerManager();
        }
        return INSTANCE;
    }

    public synchronized GuildMusicManager getGuildMusicManager(long guildId) {
        GuildMusicManager musicManager = musicManagers.get(guildId);

        if (musicManager == null) {
            musicManager = new GuildMusicManager(playerManager);
            musicManagers.put(guildId, musicManager);
        }

        return musicManager;
    }

    public void loadAndPlay(GuildMusicManager musicManager, String trackUrl, TrackLoadCallback callback) {
        playerManager.loadItemOrdered(musicManager, trackUrl, callback);
    }

    public AudioPlayerManager getAudioPlayerManager() {
        return playerManager;
    }
}
