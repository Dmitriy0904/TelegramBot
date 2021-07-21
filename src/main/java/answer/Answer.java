package answer;

import commands.*;
import commands.def.DefaultCommand;
import commands.def.DefaultCommandFactory;
import commands.find.FindCommand;
import commands.find.FindCommandFactory;
import commands.help.HelpCommand;
import commands.help.HelpCommandFactory;
import commands.settings.SettingsCommand;
import commands.settings.SettingsCommandFactory;
import commands.start.StartCommand;
import commands.start.StartCommandFactory;
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
                StartCommand startCommand = StartCommandFactory.getInstance().getStartCommand();
                return startCommand.formStartAnswer();
            }
            case FIND -> {
                FindCommand findCommand = FindCommandFactory.getInstance().getFindCommand();
                return findCommand.formFindAnswer(message);
            }
            case HELP -> {
                HelpCommand helpCommand = HelpCommandFactory.getInstance().getHelpCommand();
                return helpCommand.formHelpAnswer();
            }
            case SETTINGS -> {
                SettingsCommand settingsCommand = SettingsCommandFactory.getInstance().getSettingsCommand();
                return settingsCommand.formSettingsAnswer();
            }
            default -> {
                DefaultCommand defaultCommand = DefaultCommandFactory.getInstance().getDefaultCommand();
                return defaultCommand.formDefaultAnswer();
            }
        }
    }
}