package props.bot;

import bot.Bot;
import annotation.Active;
import props.Destination;
import props.PropertyLoader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Active
@Destination(destination = Bot.class)
public class BotPropertyLoader implements PropertyLoader {

    @Override
    public Properties loadProperties(String path) {
        Properties props = new Properties();

        try(InputStream inputStream = this.getClass().getResourceAsStream(path)){
            props.load(inputStream);
            return props;

        } catch (FileNotFoundException exception){
            throw new RuntimeException(exception);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}


//
//            Map<Field, String> propertyValue = new HashMap<>();
//            Field[] objectFields = Bot.class.getDeclaredFields();
//
//            for(Field field : objectFields){
//                if(field.isAnnotationPresent(BotProperty.class)){
//                    BotProperty botProperty = field.getAnnotation(BotProperty.class);
//                    String value = botProperty.value();
//                    propertyValue.put(field, value);
//                }
//            }
//
//            return propertyValue;