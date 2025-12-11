package commands;

public interface Command {

    String getName();

    void execute(CommandContext context, String[] args);

}
