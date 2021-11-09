package telegram;

import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramAPI extends TelegramLongPollingBot {
    private String TOKEN = "";
    private String USERID ="" ;
    public TelegramAPI(DefaultBotOptions options){super(options);}

    @Override
    public String getBotUsername() {
        return USERID;
    }

    @Override
    public String getBotToken() {
        return TOKEN;
    }

    public void receive(Update update){
        if (update.getMessage()!=null && update.getMessage().hasText()){
            long chat_id = update.getMessage().getChatId();
            try{
                String chatIdAsString = String.valueOf(chat_id);
                execute(new SendMessage(chatIdAsString, "privet"));
            } catch(TelegramApiException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onUpdateReceived(Update update) {

    }
}
