package answer;

import commands.CommandsType;
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
        CommandsType answerType = messageParser.parseMessage(message);
        switch (answerType){
            case FIND -> {
                return "Finding city";
            }
            case HELP -> {
                return "This is info";
            }
            case SETTINGS -> {
                return "Something settings";
            }
            default -> {
                return "Incorrect command";
            }
        }
    }
}
