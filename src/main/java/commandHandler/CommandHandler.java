package commandHandler;

import outputModule.OutputModule;
import tokenizer.Token;
import tokenizer.Tokenizer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class CommandHandler {
    private final Tokenizer tokenizer;
    private static OutputModule outputModule;
    private final Map<String, Consumer<Token>> commands;

    public CommandHandler(Tokenizer tokenizer, OutputModule outputModule) {
        this.tokenizer = tokenizer;
        CommandHandler.outputModule = outputModule;
        commands = getCommands();
        handle();
    }

    public void handle() {
        while (true) {
            Token token = null;
            while (token == null)
                token = tokenizer.getNextToken();
            handle(token);
        }
    }

    private void handle(Token token) {
        String[] tokenCommands = token.commands();
        String firstCommand = tokenCommands[0];
        String[] arguments = Arrays
                .stream(tokenCommands)
                .toList()
                .subList(1, tokenCommands.length)
                .toArray(String[]::new);
        Consumer<Token> command;
        command = commands.get(firstCommand);
        if (command == null) {
            outputModule.sendMessage(String.format("Неизвестная команда %s", firstCommand), token.userId());
            return;
        }
        command.accept(new Token(arguments, token.userId()));
    }

    private Map<String, Consumer<Token>> getCommands() {
        final Map<String, Consumer<Token>> commands;
        commands = new HashMap<>();
        commands.put("/help", CommandHandler::help);
        return commands;
    }

    private static void help(Token token) {
        outputModule.sendMessage("Типа help", token.userId());
    }
}
