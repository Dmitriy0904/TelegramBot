package bot;

import answer.Answer;
import config.PathConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import props.PropertyLoader;
import props.PropertyLoaderFactory;
import props.bot.BotProperty;

import java.util.Properties;


public class Bot extends TelegramLongPollingBot {
    private static final Logger log = LoggerFactory.getLogger(Bot.class);
    @BotProperty("token")
    private final String token;
    @BotProperty("username")
    private final String username;

    public Bot(){
        log.info("Starting bot. Trying to load properties");
        PropertyLoader propertyLoader = PropertyLoaderFactory.getInstance().getPropertyLoader(this.getClass());
        Properties properties = propertyLoader.loadProperties(PathConfig.BOT_PROPERTIES_PATH.getPath());
        //КОРРЕКТНО УСТАНОВИТЬ ПРОПЕРТИ
        this.token = properties.getProperty("token");
        this.username = properties.getProperty("username");
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
        //sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId());     //Установил айди чата в который бот должен ответить
//        sendMessage.setReplyToMessageId(message.getMessageId());    //Установил на какое именно сообщение бот должен ответить
        try {
//            setButtons(sendMessage);
            execute(sendMessage.setText(text));
        } catch (TelegramApiException exception) {
            log.error("TelegramApiException in sending message to {} chat {}", userName, chatId);
            throw new RuntimeException(exception);
        }
        log.info("Message to {} chat {} was sent successfully", userName, chatId);
    }


//    public void setButtons(SendMessage sendMessage){
//        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
//        sendMessage.setReplyMarkup(keyboardMarkup);
//        keyboardMarkup.setResizeKeyboard(true);       //Подгонка клавиатуры под кол-во кнопок
//        keyboardMarkup.setOneTimeKeyboard(false);     //После нажатия на кнопку клавиатура не будет закрыта
//        keyboardMarkup.setSelective(true);        //Клавиатура доступна всем
//
//        List<KeyboardRow> rows = new ArrayList<>();
//        KeyboardRow firstRow = new KeyboardRow();
//        firstRow.add(new KeyboardButton("/help"));
//        firstRow.add(new KeyboardButton("/find"));
//        firstRow.add(new KeyboardButton("/settings"));
//        rows.add(firstRow);
//
//        keyboardMarkup.setKeyboard(rows);
//    }



    //Получение имени бота
    @Override
    public String getBotUsername() {
        return username;
    }

    //Получение токена
    @Override
    public String getBotToken() {
        return token;
    }
}
