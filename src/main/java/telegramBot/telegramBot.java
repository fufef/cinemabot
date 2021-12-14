package telegramBot;

import botLogic.BotLogic;
import botLogic.commands.NewsCommand;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.request.SendMessage;
import dataIO.consoleInput.ConsoleInput;
import dataIO.inputModule.Lexeme;
import tokenizer.Tokenizer;

import java.util.Date;

public class telegramBot {
    public telegramBot(String token)
    {
        var bot = new TelegramBot(token);
        var logic = new BotLogic();
        var tokenizer = new Tokenizer(new ConsoleInput());

        new Thread(() -> {
            try {
                checkForUpdates(logic, bot, 24 * 60 * 60 * 1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        bot.setUpdatesListener(updates -> {
            for(var update : updates)
            {
                if(update.message() != null && update.message().text() != null)
                {
                    var message = update.message().text();
                    var chatId = update.message().chat().id();
                    logic.lastUserMessages.put(chatId.toString(), new Date());
                    bot.execute(new SendMessage(chatId,
                            logic.handle(tokenizer.getTokenByLexem(new Lexeme(message, chatId.toString())))));
                }
            }

            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }

    private void checkForUpdates(BotLogic logic, TelegramBot bot, int periodInMilliseconds) throws Exception {
        while (true)
        {
            if(new Date().getTime() - logic.lastCheckTime.getTime() >= periodInMilliseconds) //24 * 60 * 60 * 1000
            {
                for(var user : logic.lastUserMessages.keySet())
                {
                    if(new Date().getTime() - logic.lastUserMessages.get(user).getTime() >= periodInMilliseconds)
                    {
                        bot.execute(new SendMessage(user, NewsCommand.release()));
                    }
                }
                logic.lastCheckTime = new Date();
            }
        }
    }
}
