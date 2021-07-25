package commands.find;

import annotation.Active;
import commands.CommandsPackages;
import org.reflections.Reflections;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;
import java.util.stream.Collectors;

public class FindCommandFactory {
    private static FindCommandFactory instance;
    private Reflections reflections;
    private Set<Class<? extends FindCommand>> implementations;
    private String rootPackage;


    private FindCommandFactory() {
        rootPackage = CommandsPackages.FIND.getROOT_PACKAGE();
        reflections = new Reflections(rootPackage);
        implementations = reflections.getSubTypesOf(FindCommand.class);
    }


    public static synchronized FindCommandFactory getInstance(){
        if(instance == null){
            instance = new FindCommandFactory();
        }
        return instance;
    }


    public FindCommand getFindCommand(){
        Set<Class<? extends FindCommand>> candidates = implementations
                .stream()
                .filter(findCommandImpl -> findCommandImpl.isAnnotationPresent(Active.class))
                .collect(Collectors.toSet());

        if(candidates.isEmpty()){
            throw new RuntimeException("There are no find command implementation");
        }

        for(Class<? extends FindCommand> findCommandImpl : candidates){
            if(findCommandImpl.isAnnotationPresent(Active.class)){
                try {
                    return findCommandImpl.getDeclaredConstructor().newInstance();

                } catch (InstantiationException exception) {
                    throw new RuntimeException("InstantiationException in getFindCommand message");

                } catch (IllegalAccessException exception) {
                    throw new RuntimeException("IllegalAccessException in getFindCommand message");

                } catch (InvocationTargetException exception) {
                    throw new RuntimeException("InvocationTargetException in getFindCommand message");

                } catch (NoSuchMethodException exception) {
                    throw new RuntimeException("NoSuchMethodException in getFindCommand message");
                }
            }
        }

        throw new RuntimeException("Can not create new find command instance");
    }
}