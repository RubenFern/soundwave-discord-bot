package actions;

import commands.CommandContext;
import lavaplayer.GuildMusicManager;
import lavaplayer.PlayerManager;
import lavaplayer.TrackLoadCallback;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.middleman.AudioChannel;
import net.dv8tion.jda.api.managers.AudioManager;

@Slf4j
public class PlayAction implements Action {

    @Override
    public void action(CommandContext context, String[] args) {
        var event = context.getSlashEvent();

        if (args.length == 0) {
            event.reply("Uso: `/play <url o búsqueda>`").setEphemeral(true).queue();
            return;
        }

        String query = args[0];
        Guild guild = event.getGuild();
        Member member = event.getMember();

        if (guild == null || member == null || member.getVoiceState() == null || !member.getVoiceState().inAudioChannel()) {
            event.reply("Debes estar en un canal de voz para reproducir música.").setEphemeral(true).queue();
            return;
        }

        AudioChannel audioChannel = member.getVoiceState().getChannel();

        if (audioChannel == null) {
            event.reply("No se pudo obtener el canal de voz.").setEphemeral(true).queue();
            return;
        }

        AudioManager audioManager = guild.getAudioManager();

        PlayerManager playerManager = PlayerManager.getInstance();
        GuildMusicManager musicManager = playerManager.getGuildMusicManager(guild.getIdLong());

        if (audioManager.getSendingHandler() == null) {
            audioManager.setSendingHandler(musicManager.sendHandler);
        }

        // Ensure the bot is deafened to avoid echo
        audioManager.setSelfDeafened(true);

        audioManager.openAudioConnection(audioChannel);

        String trackIdentifier = isUrl(query) ?
                query :
                "ytsearch:" + query;

        log.info("Track identifier: {}", trackIdentifier);

        event.deferReply().queue();

        playerManager.getAudioPlayerManager()
                .loadItemOrdered(musicManager, trackIdentifier, new TrackLoadCallback(event, musicManager, trackIdentifier, query));
    }

    private boolean isUrl(String s) {
        return s.startsWith("http://") || s.startsWith("https://");
    }

}
