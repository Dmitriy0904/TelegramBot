package answer;

import commands.CommandsType;

public class UserRequest {
    private CommandsType commandType;
    private String value;

    public UserRequest(CommandsType commandType, String value) {
        this.commandType = commandType;
        this.value = value;
    }

    public CommandsType getCommandType() {
        return commandType;
    }

    public String getValue() {
        return value;
    }
}
