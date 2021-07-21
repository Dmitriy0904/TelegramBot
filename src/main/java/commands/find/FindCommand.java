package commands.find;

import org.telegram.telegrambots.api.objects.Message;

public interface FindCommand {
    String formFindAnswer(Message message);
}
