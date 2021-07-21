package commands.start;

import commands.Active;
import commands.CommandsPackages;
import org.reflections.Reflections;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;
import java.util.stream.Collectors;


public class StartCommandFactory {
    private static StartCommandFactory instance;
    private final Reflections reflections;
    private final Set<Class<? extends StartCommand>> implementations;
    private final String ROOT_PACKAGE;


    private StartCommandFactory(){
        ROOT_PACKAGE = CommandsPackages.START.getROOT_PACKAGE();
        reflections = new Reflections(ROOT_PACKAGE);
        implementations = reflections.getSubTypesOf(StartCommand.class);
    }


    public static synchronized StartCommandFactory getInstance(){
        if(instance == null){
            instance = new StartCommandFactory();
        }
        return instance;
    }


    public StartCommand getStartCommand(){
        Set<Class<? extends StartCommand>> candidates = implementations
                .stream()
                .filter(startImpl -> startImpl.isAnnotationPresent(Active.class))
                .collect(Collectors.toSet());

        if(candidates.isEmpty()){
            throw new RuntimeException("There are no start command implementation");
        }

        for(Class<? extends StartCommand> startCommandImpl : candidates){
            if(startCommandImpl.isAnnotationPresent(Active.class)){
                try {
                    return startCommandImpl.getDeclaredConstructor().newInstance();
                } catch (InstantiationException e) {
                    throw new RuntimeException("InstantiationException in getStartCommand method");
                } catch (IllegalAccessException exception) {
                    throw new RuntimeException("IllegalAccessException in getStartCommand method");
                } catch (InvocationTargetException e) {
                    throw new RuntimeException("InvocationTargetException in getStartCommand method");
                } catch (NoSuchMethodException exception) {
                    throw new RuntimeException("NoSuchMethodException in getStartCommand method");
                }
            }
        }

        throw new RuntimeException("Cannot create new start command instance");
    }
}