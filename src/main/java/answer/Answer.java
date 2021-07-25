package answer;

import commands.factory.CommandFactory;
import commands.factory.CommandsFactoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.api.objects.Message;


public class Answer {
    private final MessageParser messageParser;
    private static final Logger log = LoggerFactory.getLogger(Answer.class);

    public Answer() {
        messageParser = new MessageParser();
    }

    public String formAnswer(Message message){
        CommandFactory commandFactory = new CommandsFactoryImpl();
        UserRequest userRequest = messageParser.parseMessage(message);

        return switch (userRequest.getCommandType()) {
            case START -> commandFactory.getStartCommand().formStartAnswer();
            case FIND -> commandFactory.getFindCommand().formFindAnswer(userRequest);
            case HELP -> commandFactory.getHelpCommand().formHelpAnswer();
            case SETTINGS -> commandFactory.getSettingsCommand().formSettingsAnswer();
            default -> commandFactory.getDefaultCommand().formDefaultAnswer(userRequest);
        };
    }
}