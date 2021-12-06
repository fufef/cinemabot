package bots.consoleBot;

import botLogic.BotLogic;
import com.pengrad.telegrambot.TelegramBot;
import dataIO.consoleInput.ConsoleInput;
import dataIO.consoleOutput.ConsoleOutput;
import dataIO.inputModule.Lexeme;
import dataIO.outputModule.OutputModule;
import tokenizer.Token;
import tokenizer.Tokenizer;

import java.util.Date;

public class ConsoleBot {
    private final OutputModule outputModule;
    private final Tokenizer tokenizer;
    public final BotLogic botLogic;

    public ConsoleBot(TelegramBot bot) {
        this.outputModule = new ConsoleOutput();
        this.tokenizer = new Tokenizer(new ConsoleInput());
        this.botLogic = new BotLogic(bot);
    }

    public String getAnswerOnMessage(String message, String id) {
        var token = tokenizer.getTokenByLexem(new Lexeme(message, id));
        botLogic.lastUserMessages.put(id, new Date());
        return botLogic.handle(token);
    }

    public void start() {
        while (true) {
            Token token = null;
            while (token == null)
                token = tokenizer.getTokenByLexem();
            String message = botLogic.handle(token);
            outputModule.sendMessage(message, token.userId());
        }
    }
}
