package commands.find.weather.model;

import config.PathConfig;
import props.PropertyLoader;
import props.PropertyLoaderFactory;
import props.weather.WeatherRequestProperty;

import java.util.Properties;

public class WeatherRequest {
    @WeatherRequestProperty("url_template")
    private String urlTemplate;

    @WeatherRequestProperty("city")
    private String city;

    @WeatherRequestProperty("url_parameters")
    private String urlParameters;

    @WeatherRequestProperty("key")
    private String key;

    public WeatherRequest(String city) {
        PropertyLoader propertyLoader = PropertyLoaderFactory.getInstance().getPropertyLoader(this.getClass());
        Properties properties = propertyLoader.loadProperties(PathConfig.WEATHER_PROPERTIES_PATH.getPath());
        //КОРРЕКТНО УСТАНОВИТЬ ПРОПЕРТИ
        urlTemplate = properties.getProperty("url_template");
        urlParameters = properties.getProperty("url_parameters");
        key = properties.getProperty("key");
        this.city = city;
    }

    @Override
    public String toString() {
        return  urlTemplate + city + urlParameters + key;
    }
}
