package commands.settings;

import annotation.Active;

@Active
public class SettingsCommandImpl implements SettingsCommand{
	@Override
    public String formSettingsAnswer(){
        return "You pressed settings";
    }
}
