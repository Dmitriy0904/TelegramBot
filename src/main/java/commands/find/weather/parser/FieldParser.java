package commands.find.weather.parser;

import java.lang.reflect.Field;

public class FieldParser {

    //Метод определяет тип поля объекта и записывает в него значение value
    public <T> void parseField(T object, Field fieldToParse, String value){
        Class<?> typeOfField = fieldToParse.getType();
        try {

            if(typeOfField.isPrimitive()){

                if(typeOfField.isAssignableFrom(byte.class))
                    fieldToParse.set(object, Byte.parseByte(value));

                else if(typeOfField.isAssignableFrom(short.class))
                    fieldToParse.set(object, Short.parseShort(value));

                else if(typeOfField.isAssignableFrom(int.class))
                    fieldToParse.set(object, Integer.parseInt(value));

                else if(typeOfField.isAssignableFrom(long.class))
                    fieldToParse.set(object, Long.parseLong(value));

                else if(typeOfField.isAssignableFrom(float.class))
                    fieldToParse.set(object, Float.parseFloat(value));

                else if(typeOfField.isAssignableFrom(double.class))
                    fieldToParse.set(object, Double.parseDouble(value));

                else if(typeOfField.isAssignableFrom(boolean.class))
                    fieldToParse.set(object, Boolean.parseBoolean(value));

                else if(typeOfField.isAssignableFrom(char.class))
                    fieldToParse.set(object, value.charAt(0));
            }
            else {
                fieldToParse.set(object, value);
            }

        } catch (IllegalAccessException exception){
            throw new RuntimeException(exception);
        }
    }
}