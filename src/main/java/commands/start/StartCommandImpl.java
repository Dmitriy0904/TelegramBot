package commands.start;

import annotation.Active;

@Active
public class StartCommandImpl implements StartCommand {
	@Override
    public String formStartAnswer(){
        return "Hello from bot. The bot is already running";
    }
}
