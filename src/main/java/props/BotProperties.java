package props;

public enum BotProperties {
    TOKEN("token"),
    USERNAME("username");

    private final String property;

    BotProperties(String property) {
        this.property = property;
    }

    public String getProperty() {
        return property;
    }
}
