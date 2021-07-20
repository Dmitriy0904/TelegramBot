package answer;

import commands.CommandsType;
import org.telegram.telegrambots.api.objects.Message;


public class MessageParser {

    public CommandsType parseMessage(Message message){
        if(message == null || !message.hasText()){
            return CommandsType.DEFAULT;
        }
        //switch??
        String text = message.getText();
        if(text.toLowerCase().contains(CommandsType.FIND.getCommand())){
            return CommandsType.FIND;
        }
        else if(text.toLowerCase().equals(CommandsType.HELP.getCommand())){
            return CommandsType.HELP;
        }
        else if(text.toLowerCase().equals(CommandsType.SETTINGS.getCommand())){
            return CommandsType.SETTINGS;
        } else if(text.toLowerCase().equals(CommandsType.START.getCommand())){
            return CommandsType.START;
        }
        return CommandsType.DEFAULT;

    }
}
