package answer;

import commands.CommandsType;
import org.telegram.telegrambots.api.objects.Message;

public class MessageParser {
    private final Integer MAX_MESSAGE_PARTS = 2;

    public UserRequest parseMessage(Message message){

        if(message == null || !message.hasText()){
            new UserRequest(CommandsType.DEFAULT, null);
        }
        //switch??
        String text = message.getText();
        if(text.toLowerCase().contains(CommandsType.FIND.getCommand())){
            String[] info = text.split(" ", MAX_MESSAGE_PARTS);
            if(info.length == 1){           //Если пользователь ввёл только команду /find без указания города
                return new UserRequest(CommandsType.DEFAULT);
            }
            String city = info[1].strip();
            return new UserRequest(CommandsType.FIND, city);
        }
        else if(text.equalsIgnoreCase(CommandsType.HELP.getCommand())){
            return new UserRequest(CommandsType.HELP);
        }
        else if(text.equalsIgnoreCase(CommandsType.SETTINGS.getCommand())){
            return new UserRequest(CommandsType.SETTINGS);
        }
        else if(text.equalsIgnoreCase(CommandsType.START.getCommand())){
            return new UserRequest(CommandsType.START);
        }
        return new UserRequest(CommandsType.DEFAULT);
    }
}
