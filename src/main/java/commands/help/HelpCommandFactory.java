package commands.help;

import commands.Active;
import commands.CommandsPackages;
import commands.start.StartCommand;
import commands.start.StartCommandFactory;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;
import java.util.stream.Collectors;

public class HelpCommandFactory {
    private static HelpCommandFactory instance;
    private final Reflections reflections;
    private final Set<Class<? extends HelpCommand>> implementations;
    private final String ROOT_PACKAGE;


    private HelpCommandFactory(){
        ROOT_PACKAGE = CommandsPackages.HELP.getROOT_PACKAGE();
        reflections = new Reflections(ROOT_PACKAGE);
        implementations = reflections.getSubTypesOf(HelpCommand.class);
    }


    public static synchronized HelpCommandFactory getInstance(){
        if(instance == null){
            instance = new HelpCommandFactory();
        }
        return instance;
    }


    public HelpCommand getHelpCommand(){
        Set<Class<? extends HelpCommand>> candidates = implementations
                .stream()
                .filter(startImpl -> startImpl.isAnnotationPresent(Active.class))
                .collect(Collectors.toSet());

        if(candidates.isEmpty()){
            throw new RuntimeException("There are no help command implementation");
        }

        for(Class<? extends HelpCommand> helpCommandImpl : candidates){
            if(helpCommandImpl.isAnnotationPresent(Active.class)){
                try {
                    return helpCommandImpl.getDeclaredConstructor().newInstance();
                } catch (InstantiationException e) {
                    throw new RuntimeException("InstantiationException in getHelpCommand method");
                } catch (IllegalAccessException exception) {
                    throw new RuntimeException("IllegalAccessException in getHelpCommand method");
                } catch (InvocationTargetException e) {
                    throw new RuntimeException("InvocationTargetException in getHelpCommand method");
                } catch (NoSuchMethodException exception) {
                    throw new RuntimeException("NoSuchMethodException in getHelpCommand method");
                }
            }
        }

        throw new RuntimeException("Cannot created new instance of help command");
    }
}
