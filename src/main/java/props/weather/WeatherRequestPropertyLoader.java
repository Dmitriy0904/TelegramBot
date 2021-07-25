package props.weather;

import annotation.Active;
import commands.find.weather.model.WeatherRequest;
import props.Destination;
import props.PropertyLoader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Active
@Destination(destination = WeatherRequest.class)
public class WeatherRequestPropertyLoader implements PropertyLoader {

    @Override
    public Properties loadProperties(String path) {
        Properties props = new Properties();

        try(InputStream inputStream = this.getClass().getResourceAsStream(path)) {
            props.load(inputStream);
            return props;
        } catch (FileNotFoundException exception){
            throw new RuntimeException(exception);
        } catch (IOException exception){
            throw new RuntimeException(exception);
        }

    }
}
