package config;

public enum PathConfig {
    BOT_PROPERTIES_PATH("src/main/resources/bot.properties"),
    WEATHER_PROPERTIES_PATH("src/main/resources/request.properties");

    private final String path;

    PathConfig(String path) {
        this.path = path;
    }

    public String getPath(){
        return path;
    }

}
