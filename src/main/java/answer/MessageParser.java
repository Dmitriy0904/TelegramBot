package answer;

import commands.CommandsType;
import commands.IncorrectCommandReason;
import org.apache.commons.lang3.StringUtils;
import org.telegram.telegrambots.api.objects.Message;


public class MessageParser {
    private final Integer MAX_MESSAGE_PARTS = 2;

    public UserRequest parseMessage(Message message){

        if(message == null || !message.hasText()){
            new UserRequest(CommandsType.DEFAULT, null);
        }

        //switch??
        String text = message.getText();
        if(StringUtils.containsIgnoreCase(text, CommandsType.FIND.getCommand())){
            String[] info = text.split(" ", MAX_MESSAGE_PARTS);
            if(info.length == 1){           //Если пользователь ввёл только команду /find без указания города
                return new UserRequest(CommandsType.DEFAULT, IncorrectCommandReason.FIND_WITHOUT_CITY.getReason());
            }
            String city = info[1].strip();
            return new UserRequest(CommandsType.FIND, city);
        }
        else if(StringUtils.equalsIgnoreCase(text, CommandsType.HELP.getCommand())){
            return new UserRequest(CommandsType.HELP);
        }
        else if(StringUtils.equalsIgnoreCase(text, CommandsType.SETTINGS.getCommand())){
            return new UserRequest(CommandsType.SETTINGS);
        }
        else if(StringUtils.equalsIgnoreCase(text, CommandsType.START.getCommand())){
            return new UserRequest(CommandsType.START, IncorrectCommandReason.INCORRECT_COMMAND.getReason());
        }
        return new UserRequest(CommandsType.DEFAULT);
    }
}