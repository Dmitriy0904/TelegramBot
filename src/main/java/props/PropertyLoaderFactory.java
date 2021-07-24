package props;

import annotation.Active;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;
import java.util.stream.Collectors;

public class PropertyLoaderFactory {
    private static PropertyLoaderFactory instance;
    private Reflections reflections;
    private Set<Class<? extends PropertyLoader>> implementations;


    private PropertyLoaderFactory(){
        reflections = new Reflections("props");
    }

    public static synchronized PropertyLoaderFactory getInstance(){
        if(instance == null){
            instance = new PropertyLoaderFactory();
        }
        return instance;
    }


    public PropertyLoader getPropertyLoader(Class<?> objectClass) {
        implementations = reflections.getSubTypesOf(PropertyLoader.class);
        Set<Class<? extends PropertyLoader>> candidates = implementations
                .stream()
                .filter(defImpl -> defImpl.isAnnotationPresent(Active.class))
                .filter(defImpl -> defImpl.isAnnotationPresent(Destination.class))
                .collect(Collectors.toSet());

        if (candidates.isEmpty()) {
            throw new RuntimeException("There are no property loader implementations");
        }

        for (Class<? extends PropertyLoader> impl : candidates) {
            Destination destination = impl.getAnnotation(Destination.class);
            if (destination.destination() == objectClass) {
                try {
                    return impl.getDeclaredConstructor().newInstance();
                } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException exception) {
                    throw new RuntimeException(exception);
                }
            }
        }
        throw new RuntimeException("Can not create new instance");
    }

}