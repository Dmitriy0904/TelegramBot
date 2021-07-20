package commands.start;

import commands.Active;

@Active()
public class StartCommandCommandImpl implements StartCommand {
    public String formStartAnswer(){
        return "Hello from bot. The bot is already running";
    }
}
