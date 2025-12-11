package commands;

import java.util.HashMap;
import java.util.Map;

public class CommandRegistry {

    private final Map<String, Command> commands = new HashMap<>();

    public void register(Command command) {
        commands.put(getCommandKey(command.getName()), command);
    }

    public Command getCommand(String name) {
        return commands.get(getCommandKey(name));
    }

    private String getCommandKey(String name) {
        return name.toLowerCase().trim();
    }

}
