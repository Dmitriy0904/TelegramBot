package props.weather;

import annotation.Active;
import commands.find.weather.model.WeatherRequest;
import props.Destination;
import props.PropertyLoader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

@Active
@Destination(destination = WeatherRequest.class)
public class WeatherRequestPropertyLoader implements PropertyLoader {

    @Override
    public Properties loadProperties(String path) {
        Properties props = new Properties();

        try(FileInputStream fileInputStream = new FileInputStream(path)){
            props.load(fileInputStream);
            return props;

//            Map<Field, String> propertyValue = new HashMap<>();
//            Field[] objectFields = WeatherRequest.class.getDeclaredFields();
//
//            for(Field field : objectFields){
//                if(field.isAnnotationPresent(WeatherRequestProperty.class)){
//                    WeatherRequestProperty botProperty = field.getAnnotation(WeatherRequestProperty.class);
//                    String value = botProperty.value();
//                    propertyValue.put(field, value);
//                }
//            }
//
//            return propertyValue;

        } catch (FileNotFoundException exception) {
            throw new RuntimeException(exception);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

    }
}
