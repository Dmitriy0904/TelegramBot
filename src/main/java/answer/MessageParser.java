package answer;

import commands.CommandsType;
import org.telegram.telegrambots.api.objects.Message;

public class MessageParser {
    private final Integer MAX_MESSAGE_PARTS = 2;

    public UserQuery parseMessage(Message message){

        if(message == null || !message.hasText()){
            new UserQuery(CommandsType.DEFAULT, null);
        }
        //switch??
        String text = message.getText();
        if(text.toLowerCase().contains(CommandsType.FIND.getCommand())){
            String[] info = text.split(" ", MAX_MESSAGE_PARTS);
            String city = info[1].strip();
            return new UserQuery(CommandsType.FIND, city);
        }
        else if(text.equalsIgnoreCase(CommandsType.HELP.getCommand())){
            return new UserQuery(CommandsType.HELP, null);
        }
        else if(text.equalsIgnoreCase(CommandsType.SETTINGS.getCommand())){
            return new UserQuery(CommandsType.SETTINGS, null);
        }
        else if(text.equalsIgnoreCase(CommandsType.START.getCommand())){
            return new UserQuery(CommandsType.START, null);
        }
        return new UserQuery(CommandsType.DEFAULT, null);
    }
}
