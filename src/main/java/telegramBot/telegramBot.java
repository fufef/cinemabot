package telegramBot;

import bots.consoleBot.ConsoleBot;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.request.SendMessage;

public class telegramBot {
    public telegramBot(String token)
    {
        var bot = new TelegramBot(token);
        var consoleBot = new ConsoleBot();

        bot.setUpdatesListener(updates -> {
            for(var update : updates)
            {
                if(update.message() != null)
                {
                    var message = update.message().text();
                    var chatId = update.message().chat().id();
                    bot.execute(new SendMessage(chatId, consoleBot.getAnswerOnMessage(message, chatId.toString())));
                }
            }

            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }
}
