package commands;

public enum IncorrectCommandReason {
    INCORRECT_CITY("Such city does not exist"),
    FIND_WITHOUT_CITY("You should indicate the city the weather of which you want to receive after the /find request"),
    INCORRECT_COMMAND("Such command does not exist");

    private String reason;

    IncorrectCommandReason(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }
}
