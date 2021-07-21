package commands.settings;

import commands.Active;

@Active()
public class SettingsCommandImpl implements SettingsCommand{
    public String formSettingsAnswer(){
        return "You pressed settings";
    }
}
