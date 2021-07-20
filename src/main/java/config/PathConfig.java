package config;

public enum PathConfig {
    BOT_PROPERTIES_PATH("src/main/resources/bot.properties");

    private final String path;

    PathConfig(String path) {
        this.path = path;
    }

    public String getPath(){
        return path;
    }

}
