package commands.help;

import commands.Active;

@Active()
public class HelpCommandImpl implements HelpCommand{
    public String formHelpAnswer(){
        return "You pressed help";
    }
}