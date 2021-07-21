package commands.find;

import commands.Active;
import commands.CommandsType;
import org.telegram.telegrambots.api.objects.Message;

@Active()
public class FindCommandImpl implements FindCommand {
    private final int MAX_PARTS = 2;

    public String formFindAnswer(Message message){
        String text = message.getText();
        String[] info = text.split(CommandsType.FIND.getCommand(), MAX_PARTS);
        String city = info[1];      //говнокод. ИСПРАВИТЬ
        return "Weather to city " + city;
    }
}
