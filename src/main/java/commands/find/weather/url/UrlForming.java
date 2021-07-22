package commands.find.weather.url;

import config.PathConfig;
import props.PropsLoader;
import props.WeatherProps;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class UrlForming{
    private String KEY;
    private final String URL_TEMPLATE;
    private final String URL_PARAMETERS;

    public UrlForming() {
        PropsLoader propsLoader = new PropsLoader();
        Properties properties = propsLoader.loadProperties(PathConfig.WEATHER_PROPERTIES_PATH.getPath());
        KEY = properties.getProperty(WeatherProps.KEY.getPropertyName());
        URL_TEMPLATE = properties.getProperty(WeatherProps.URL_TEMPLATE.getPropertyName());
        URL_PARAMETERS = properties.getProperty(WeatherProps.URL_PARAMETERS.getPropertyName());
    }

    public URL formUrl(String city){
        try {
            StringBuilder urlBuilder = new StringBuilder("");
            urlBuilder.append(URL_TEMPLATE).append(city).append(URL_PARAMETERS).append(KEY);
            return new URL(urlBuilder.toString());

        } catch (MalformedURLException exception) {
            throw new RuntimeException("MalformedURLException in formUrl method");
        }
    }
}