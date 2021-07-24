package commands.find.weather.url;

import commands.find.weather.model.WeatherRequest;
import java.net.MalformedURLException;
import java.net.URL;

public class UrlForming{

    public URL formUrl(String city){
        try {
            WeatherRequest weatherRequest = new WeatherRequest(city);
            String urlRequest = weatherRequest.toString();
            return new URL(urlRequest);

        } catch (MalformedURLException exception) {
            throw new RuntimeException("MalformedURLException in formUrl method");
        }
    }
}