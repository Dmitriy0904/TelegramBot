package commands.factory;

import commands.def.DefaultCommand;
import commands.find.FindCommand;
import commands.help.HelpCommand;
import commands.settings.SettingsCommand;
import commands.start.StartCommand;

public interface CommandFactory {
    DefaultCommand getDefaultCommand();
    FindCommand getFindCommand();
    HelpCommand getHelpCommand();
    SettingsCommand getSettingsCommand();
    StartCommand getStartCommand();
}