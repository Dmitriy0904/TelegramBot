package commands.factory;

import commands.def.DefaultCommand;
import commands.def.DefaultCommandFactory;
import commands.find.FindCommand;
import commands.find.FindCommandFactory;
import commands.help.HelpCommand;
import commands.help.HelpCommandFactory;
import commands.settings.SettingsCommand;
import commands.settings.SettingsCommandFactory;
import commands.start.StartCommand;
import commands.start.StartCommandFactory;


public class CommandsFactoryImpl implements CommandFactory{

    @Override
    public DefaultCommand getDefaultCommand() {
        return DefaultCommandFactory.getInstance().getDefaultCommand();
    }

    @Override
    public FindCommand getFindCommand() {
        return FindCommandFactory.getInstance().getFindCommand();
    }

    @Override
    public HelpCommand getHelpCommand() {
        return HelpCommandFactory.getInstance().getHelpCommand();
    }

    @Override
    public SettingsCommand getSettingsCommand() {
        return SettingsCommandFactory.getInstance().getSettingsCommand();
    }

    @Override
    public StartCommand getStartCommand() {
        return StartCommandFactory.getInstance().getStartCommand();
    }
}