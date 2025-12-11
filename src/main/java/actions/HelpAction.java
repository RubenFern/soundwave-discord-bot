package actions;

import core.CommandContext;
import core.Commands;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;

public class HelpAction extends AbstractAction {

    @Override
    public void action(CommandContext context, String[] args) {
        var event = context.getSlashEvent();

        EmbedBuilder embed = new EmbedBuilder();

        embed.setTitle("🎶 SoundWave - Ayuda y Comandos");
        String repoUrl = "https://github.com/RubenFern/soundwave-discord-bot";
        String description = "Aquí tienes la lista de comandos disponibles.\n\n" +
                "⭐ **Si te gusta este bot, [dale una estrella en GitHub](" + repoUrl + ")**";

        embed.setDescription(description);
        embed.setColor(Color.decode("#5865F2"));
        embed.setThumbnail(event.getJDA().getSelfUser().getAvatarUrl());

        embed.addField("/" + Commands.PLAY.getName() + " `búsqueda/URL`",
                "Reproduce una canción o playlist de YouTube.", false);

        embed.addField("/" + Commands.PAUSE.getName(),
                "Pausa la canción actual.", true);

        embed.addField("/" + Commands.CONTINUE.getName(),
                "Reanuda la reproducción pausada.", true);

        embed.addField("/" + Commands.SKIP.getName(),
                "Salta a la siguiente canción de la cola.", true);

        embed.addField("/" + Commands.STOP.getName(),
                "Detiene la música, borra la cola y desconecta al bot.", false);

        embed.addField("/" + Commands.QUEUE.getName(),
                "Muestra la lista de canciones pendientes.", true);

        embed.addField("/" + Commands.NOW_PLAYING.getName(),
                "Muestra información de la canción actual.", true);

        embed.setFooter("SoundWave Bot • Hecho con Java", event.getJDA().getSelfUser().getAvatarUrl());

        event.replyEmbeds(embed.build()).queue();
    }
}