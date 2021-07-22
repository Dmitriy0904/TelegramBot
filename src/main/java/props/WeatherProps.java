package props;

public enum WeatherProps {
    KEY("key"),
    URL_TEMPLATE("url_template"),
    URL_PARAMETERS("url_parameters");

    private final String propertyName;

    WeatherProps(String property) {
        this.propertyName = property;
    }

    public String getPropertyName() {
        return propertyName;
    }
}
