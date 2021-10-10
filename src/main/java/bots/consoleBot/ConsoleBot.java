package bots.consoleBot;

import botLogic.BotLogic;
import dataIO.consoleInput.ConsoleInput;
import dataIO.consoleOutput.ConsoleOutput;
import dataIO.outputModule.OutputModule;
import tokenizer.Token;
import tokenizer.Tokenizer;

public class ConsoleBot {
    private final OutputModule outputModule;
    private final Tokenizer tokenizer;
    private final BotLogic botLogic;

    public ConsoleBot() {
        this.outputModule = new ConsoleOutput();
        this.tokenizer = new Tokenizer(new ConsoleInput());
        this.botLogic = new BotLogic();
    }

    public void start() {
        while (true) {
            Token token = null;
            while (token == null)
                token = tokenizer.getNextToken();
            String message = botLogic.handle(token);
            if (message != null)
                outputModule.sendMessage(message, token.userId());
        }
    }
}
