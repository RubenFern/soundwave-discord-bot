import commands.CommandRegistry;
import commands.Commands;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class MusicBot {

    private final CommandRegistry commandRegistry;

    public MusicBot(CommandRegistry commandRegistry)  {
        this.commandRegistry = commandRegistry;
    }

    public void start() throws InterruptedException {
        Dotenv env = Dotenv.configure()
                .ignoreIfMissing()
                .load();

        String discordToken = env.get("DISCORD_TOKEN");
        String guildId = env.get("GUILD_ID");

        if (discordToken == null || guildId == null) {
            throw new IllegalStateException("DISCORD_TOKEN and GUILD_ID must be set in the environment variables.");
        }

        JDA jda = JDABuilder.createDefault(discordToken)
                .enableIntents(GatewayIntent.GUILD_VOICE_STATES)
                .addEventListeners(new SlashCommandListener(commandRegistry))
                .build()
                .awaitReady();

        Guild guild = jda.getGuildById(guildId);

        if (guild == null) {
            System.err.println("Guild with ID " + guildId + " not found.");
            return;
        }

        registerSlashCommands(guild);
    }

    private void registerSlashCommands(Guild guild) {
        guild.upsertCommand(Commands.PLAY.getName(), "Reproduce audio desde YouTube")
                .addOption(OptionType.STRING, "query", "URL o término de búsqueda", true)
                .queue();

        guild.upsertCommand(Commands.SKIP.getName(), "Salta a la siguiente canción en la cola")
                .queue();

        guild.upsertCommand(Commands.STOP.getName(), "Detiene la reproducción y limpia la cola")
                .queue();
    }

}
