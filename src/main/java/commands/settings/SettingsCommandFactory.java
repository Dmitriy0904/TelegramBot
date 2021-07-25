package commands.settings;

import annotation.Active;
import commands.CommandsPackages;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;
import java.util.stream.Collectors;

public class SettingsCommandFactory {
    private static SettingsCommandFactory instance;
    private Reflections reflections;
    private Set<Class<? extends SettingsCommand>> implementations;
    private String rootPackage;


    private SettingsCommandFactory() {
        rootPackage = CommandsPackages.SETTINGS.getROOT_PACKAGE();
        reflections = new Reflections(rootPackage);
        implementations = reflections.getSubTypesOf(SettingsCommand.class);
    }


    public static synchronized SettingsCommandFactory getInstance(){
        if(instance == null){
            instance = new SettingsCommandFactory();
        }
        return instance;
    }


    public SettingsCommand getSettingsCommand(){
        Set<Class<? extends SettingsCommand>> candidates = implementations
                .stream()
                .filter(settingsImpl -> settingsImpl.isAnnotationPresent(Active.class))
                .collect(Collectors.toSet());

        if(candidates.isEmpty()){
            throw new RuntimeException("There are no settings command implementation");
        }

        for(Class<? extends  SettingsCommand> settingsCommandImpl : candidates){
            if(settingsCommandImpl.isAnnotationPresent(Active.class)){
                try {
                    return settingsCommandImpl.getDeclaredConstructor().newInstance();

                } catch (InstantiationException exception) {
                    throw new RuntimeException("InstantiationException in get settings command method");

                } catch (IllegalAccessException exception) {
                    throw new RuntimeException("IllegalAccessException in get settings command method");

                } catch (InvocationTargetException exception) {
                    throw new RuntimeException("InvocationTargetException in get settings command method");

                } catch (NoSuchMethodException exception) {
                    throw new RuntimeException("NoSuchMethodException in get settings command method");
                }
            }
        }

        throw new RuntimeException("Cannot create new settings command instance");
    }
}