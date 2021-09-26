package commandHandler;

import outputModule.OutputModule;
import tokenizer.Token;
import tokenizer.Tokenizer;

import java.util.Arrays;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class CommandHandler {
    private final Tokenizer tokenizer;
    private static OutputModule outputModule;
    private final Map<String, Consumer<Token>> commands;
    private static Map<String,String> helpCommands = new HashMap<String,String>(){{
        put("help", "Выводит краткую информацию о боте, список команд и их описание");
        put("advise", "Бот просит указать жанр и формат, после выдает список найденных им фильмов. По желанию, пользователь может уточнить запрос, указав параметры сортировки. После уточнения запроса бот формирует новые списки.");
        put("next", "Если в списке нет интересных для пользователя фильмов, он может попросить выдать еще подборку (список) фильмов");
        put("genre", "Сортировка по жанру/жанрам фильма Если бот встречает неизвестные ему жанры, он уведомляет об этом пользователя, предлагая справку по поддерживаемым жанрам");
        put("format ", "Выбор формата - сериал или фильм");
        put("rating", "Выбор рейтинга фильма");
        put("year", "Год выпуска");
        put("country", "Страна выпуска");
    }};;

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
        String[] arguments = token.commands();
        if (arguments.length == 0)
            outputModule.sendMessage("Типа help", token.userId());
        else {
            if (helpCommands.containsKey(arguments[0]))
                outputModule.sendMessage(String.format("%s - %s", arguments[0], helpCommands.get(arguments[0])), token.userId());
            else
                outputModule.sendMessage(String.format("Неизвестная команда %s", arguments[0]), token.userId());
        }
    }
}
