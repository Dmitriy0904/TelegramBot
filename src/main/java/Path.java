public enum Path {
    PROPERTIES_PATH("src/main/resources/bot.properties");

    private final String property;

    Path(String property) {
        this.property = property;
    }

    public String getProperty(){
        return property;
    }

}
