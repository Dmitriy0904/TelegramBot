import answer.Answer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import props.BotProperties;
import props.PropsLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class Bot extends TelegramLongPollingBot {
    private static final Logger log = LoggerFactory.getLogger(Bot.class);
    private final String TOKEN;
    private final String USERNAME;

    public Bot(){
        log.info("Starting bot. Trying to load properties");
        PropsLoader propsLoader = new PropsLoader();
        Properties properties = propsLoader.loadProperties();
        this.TOKEN = properties.getProperty(BotProperties.TOKEN.getProperty());
        this.USERNAME = properties.getProperty(BotProperties.USERNAME.getProperty());
        log.info("Properties were loaded and set successfully");
    }

    //Обновление
    @Override
    public void onUpdateReceived(Update update) {
        if(update == null || update.getMessage() == null){
            return;
        }
        Message message = update.getMessage();
        String userName = message.getFrom().getUserName();
        Long chatId = message.getChatId();
        log.info("New message from {} chat {}", userName, chatId);
        Answer answer = new Answer();
        log.info("Forming answer to {} chat {}", userName, chatId);
        String answerText = answer.formAnswer(message);
        log.info("answer.Answer to {} chat {} was formed successfully", userName, chatId);
        send(message, answerText);
    }


    public void send(Message message, String text){
        String userName = message.getFrom().getUserName();
        Long chatId = message.getChatId();
        log.info("Trying to send message to {} chat {}", userName, chatId);
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId());     //Установил айди чата в который бот должен ответить
        sendMessage.setReplyToMessageId(message.getMessageId());    //Установил на какое именно сообщение бот должен ответить
        try {
            setButtons(sendMessage);
            execute(sendMessage.setText(text));
        } catch (TelegramApiException exception) {
            log.error("TelegramApiException in sending message to {} chat {}", userName, chatId);
            throw new RuntimeException(exception);
        }
        log.info("Message to {} chat {} was sent successfully", userName, chatId);
    }


    public void setButtons(SendMessage sendMessage){
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(keyboardMarkup);
        keyboardMarkup.setResizeKeyboard(true);       //Подгонка клавиатуры под кол-во кнопок
        keyboardMarkup.setOneTimeKeyboard(false);     //После нажатия на кнопку клавиатура не будет закрыта
        keyboardMarkup.setSelective(true);        //Клавиатура доступна всем

        List<KeyboardRow> rows = new ArrayList<>();
        KeyboardRow firstRow = new KeyboardRow();
        firstRow.add(new KeyboardButton("/help"));
        firstRow.add(new KeyboardButton("/find"));
        firstRow.add(new KeyboardButton("/settings"));
        rows.add(firstRow);

        keyboardMarkup.setKeyboard(rows);
    }



    //Получение имени бота
    @Override
    public String getBotUsername() {
        return USERNAME;
    }

    //Получение токена
    @Override
    public String getBotToken() {
        return TOKEN;
    }
}
