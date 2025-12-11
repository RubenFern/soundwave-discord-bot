package commands;

import lombok.Getter;

@Getter
public enum Commands {

    PLAY("play"),
    PAUSE("pause"),
    STOP("stop"),
    SKIP("skip"),
    QUEUE("queue"),
    HISTORY("history"),
    NOW_PLAYING("nowplaying"),
    HELP("help");

    private final String name;

    Commands(String name) {
        this.name = name;
    }

}
