package commands.def;

import commands.Active;

@Active
public class DefaultCommandImpl implements DefaultCommand {
    public String formDefaultAnswer(){
        return "Bot does not know such command";
    }
}
