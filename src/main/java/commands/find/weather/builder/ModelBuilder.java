package commands.find.weather.builder;

import commands.find.weather.parser.FieldParser;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class ModelBuilder {
    private final FieldParser fieldParser;

    public ModelBuilder(){
        this.fieldParser = new FieldParser();
    }

    //Метод принимает класс объект которого нужно создать и мапу поле - значение из
    //JSON файла. Метод создаёт новую сущность переданного класса
    public <T> T buildModel(Class<T> objectClass, Map<Field, String> field_JsonValue){
        try {
            T newObject = objectClass.getDeclaredConstructor().newInstance();
            for(Field field : field_JsonValue.keySet()){
                field.setAccessible(true);
                fieldParser.parseField(newObject, field, field_JsonValue.get(field));
            }
            return newObject;
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException exception) {
            throw new RuntimeException(exception);
        }
    }
}