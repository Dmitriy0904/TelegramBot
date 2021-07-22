package commands.def;

import commands.Active;

@Active
public class DefaultCommandImpl implements DefaultCommand {
	@Override
    public String formDefaultAnswer(){
        return "Bot does not know such command";
    }
}
