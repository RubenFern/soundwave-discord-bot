import actions.PlayAction;
import commands.CommandRegistry;
import commands.PlayCommand;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("--- Iniciando SoundWave Bot ---");

            CommandRegistry commandRegistry = new CommandRegistry();
            commandRegistry.register(new PlayCommand(new PlayAction()));

            new MusicBot(commandRegistry).start();

            Thread.currentThread().join();
        } catch (Exception e) {
            Thread.currentThread().interrupt();
            System.err.printf("Error al iniciar el bot: %s", e.getMessage());
        }
    }
}
