import actions.*;
import commands.*;
import core.CommandRegistry;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("--- Iniciando SoundWave Bot ---");

            CommandRegistry commandRegistry = new CommandRegistry();

            commandRegistry.register(new PlayCommand(new PlayAction()));
            commandRegistry.register(new StopCommand(new StopAction()));
            commandRegistry.register(new PauseCommand(new PauseAction()));
            commandRegistry.register(new ContinueCommand(new ContinueAction()));
            commandRegistry.register(new SkipCommand(new SkipAction()));
            commandRegistry.register(new NowPlayingCommand(new NowPlayingAction()));
            commandRegistry.register(new QueueCommand(new QueueAction()));
            commandRegistry.register(new HelpCommand(new HelpAction()));

            new MusicBot(commandRegistry).start();

            Thread.currentThread().join();
        } catch (Exception e) {
            Thread.currentThread().interrupt();
            System.err.printf("Error al iniciar el bot: %s", e.getMessage());
        }
    }
}
