package commands.find.weather.builder;

import commands.find.weather.annotations.Mapped;
import commands.find.weather.annotations.SpecialEnd;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;

public class AnswerBuilder {
    //Метод строит ответ пользователю в зависимости от аннотированных полей в модели.
    //Если поле аннотировано Mapped то оно содердит в себе значение. Получаю это значение
    //Если поле также аннотирвоано SpecialEnd значит в конце должен стоять спец символ/-ы.
    public String buildAnswer(Object model){
        try {

            StringBuilder result = new StringBuilder("");
            Field[] objectFields = model.getClass().getDeclaredFields();

            for(Field field : objectFields){
                if(field.isAnnotationPresent(Mapped.class)){
                    field.setAccessible(true);

                    String fieldName = field.getName();
                    result.append(StringUtils.capitalize(fieldName)).append(": ");

                    String fieldValue = (field.get(model).toString());
                    result.append(StringUtils.capitalize(fieldValue));

                    if(field.isAnnotationPresent(SpecialEnd.class)){
                        SpecialEnd annotation = field.getAnnotation(SpecialEnd.class);
                        result.append(annotation.value());
                    }
                    result.append("\n");
                }
            }

            return result.toString();

        } catch (IllegalAccessException exception){
            throw new RuntimeException(exception);
        }
    }
}