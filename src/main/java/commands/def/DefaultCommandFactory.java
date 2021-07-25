package commands.def;

import annotation.Active;
import commands.CommandsPackages;
import org.reflections.Reflections;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;
import java.util.stream.Collectors;


public class DefaultCommandFactory {
    private static DefaultCommandFactory instance;
    private final Reflections reflections;
    private final Set<Class<? extends DefaultCommand>> implementations;
    private final String rootPackage;


    private DefaultCommandFactory(){
        rootPackage = CommandsPackages.DEFAULT.getROOT_PACKAGE();
        reflections = new Reflections(rootPackage);
        implementations = reflections.getSubTypesOf(DefaultCommand.class);
    }


    public static synchronized DefaultCommandFactory getInstance(){
        if(instance == null){
            instance = new DefaultCommandFactory();
        }
        return instance;
    }


    public DefaultCommand getDefaultCommand(){
        Set<Class<? extends DefaultCommand>> candidates = implementations
                .stream()
                .filter(defImpl -> defImpl.isAnnotationPresent(Active.class))
                .collect(Collectors.toSet());

        if(candidates.isEmpty()){
            throw new RuntimeException("There are no default command implementation");
        }

        for(Class<? extends DefaultCommand> defaultCommandImpl : candidates){
            if(defaultCommandImpl.isAnnotationPresent(Active.class)){
                try {
                    return defaultCommandImpl.getDeclaredConstructor().newInstance();
                } catch (InstantiationException e) {
                    throw new RuntimeException("InstantiationException in getDefaultCommand method");
                } catch (IllegalAccessException exception) {
                    throw new RuntimeException("IllegalAccessException in getDefaultCommand method");
                } catch (InvocationTargetException e) {
                    throw new RuntimeException("InvocationTargetException in getDefaultCommand method");
                } catch (NoSuchMethodException exception) {
                    throw new RuntimeException("NoSuchMethodException in getDefaultCommand method");
                }
            }
        }

        throw new RuntimeException("Cannot created new default command instance");
    }
}