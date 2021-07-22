package props;

public enum BotProps {
    TOKEN("token"),
    USERNAME("username");

    private final String propertyName;

    BotProps(String property) {
        this.propertyName = property;
    }

    public String getPropertyName() {
        return propertyName;
    }
}
