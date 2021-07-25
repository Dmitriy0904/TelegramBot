package config;

public enum PathConfig {
    BOT_PROPERTIES_PATH("/bot.properties"),
    WEATHER_PROPERTIES_PATH("/request.properties");

    private final String path;

    PathConfig(String path) {
        this.path = path;
    }

    public String getPath(){
        return path;
    }

}
