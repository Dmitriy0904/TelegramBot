package commands;

public enum CommandsType {
    START("/start"),
    HELP("/help"),
    FIND("/find"),
    SETTINGS("/settings"),
    DEFAULT(null);

    private String command;

    CommandsType(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
