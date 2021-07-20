package answer;

import commands.*;
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
            case START -> {
                StartCommand startCommand = StartCommand.getInstance();
                return startCommand.formStartAnswer();
            }
            case FIND -> {
                FindCommand findCommand = FindCommand.getInstance();
                return findCommand.formFindAnswer(message);
            }
            case HELP -> {
                HelpCommand helpCommand = HelpCommand.getInstance();
                return helpCommand.formHelpAnswer(message);
            }
            case SETTINGS -> {
                SettingsCommand settingsCommand = SettingsCommand.getInstance();
                return settingsCommand.formSettingsAnswer(message);
            }
            default -> {
                DefaultCommand defaultCommand = DefaultCommand.getInstance();
                return defaultCommand.formDefaultAnswer(message);
            }
        }
    }
}
