package commands.find.weather.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import commands.find.weather.annotations.Mapped;
import commands.find.weather.builder.ModelBuilder;
import commands.find.weather.model.WeatherModel;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ModelMapper {
    private ModelBuilder modelBuilder;
    private static ModelMapper instance;

    private ModelMapper() {
        modelBuilder = new ModelBuilder();
    }

    public static ModelMapper getInstance() {
        if(instance == null){
            instance = new ModelMapper();
        }
        return instance;
    }

    //Метод принимает класс объекта который нужно будет создать и url - запрос на json
    public <T> T mapModel(Class<T> objectClass, URL url){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(url);

            //Получаю поля помеченные аннотацией, т.е. те, которые нужно будет заполнить
            Field[] fields = WeatherModel.class.getDeclaredFields();
            List<Field> annotatedFields = new ArrayList<>();
            for (Field field : fields) {
                if(field.isAnnotationPresent(Mapped.class)){
                    annotatedFields.add(field);
                }
            }

            //Делаю хеш мапу где ключом выступает аннотированое поле которое нужно заполнить,
            //а значением - информация из json запроса
            Map<Field, String> field_JsonValue = new HashMap<>();
            for (Field field : annotatedFields) {
                Mapped mapped = field.getAnnotation(Mapped.class);
                String annotationValue = mapped.value();
                field_JsonValue.put(field, jsonNode.findValue(annotationValue).asText());
            }

            //Построение модели
            T weatherModel =  modelBuilder.buildModel(objectClass, field_JsonValue);
            return weatherModel;

        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

}