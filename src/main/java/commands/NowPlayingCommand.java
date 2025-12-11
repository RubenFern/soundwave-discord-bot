package commands;

public class NowPlayingCommand extends AbstractCommand {

    public NowPlayingCommand(core.Action action) {
        super(action);
    }

    @Override
    public String getName() {
        return core.Commands.NOW_PLAYING.getName();
    }

}
