package commandHandler;

import outputModule.OutputModule;
import tokenizer.Token;
import tokenizer.Tokenizer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CommandHandler {
    private final Tokenizer tokenizer;
    private final OutputModule outputModule;
    private final Map<String, Method> commands;
    private final CommandListener commandListener;

    public CommandHandler(Tokenizer tokenizer, OutputModule outputModule) {
        this.tokenizer = tokenizer;
        this.outputModule = outputModule;
        commandListener = new CommandListener();
        commands = new HashMap<>();
        registerCommands();
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
        Method command;
        command = commands.get(firstCommand);
        if (command == null) {
            outputModule.sendMessage(String.format("Неизвестная команда %s", firstCommand), token.userId());
            return;
        }
        try {
            var messsage = command.invoke(commandListener, new Token(arguments, token.userId()));
            System.out.println(messsage.toString());
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private void registerCommands() {
        for (Method method : commandListener.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(Command.class)) {
                Command cmd = method.getAnnotation(Command.class);
                commands.put(cmd.name(), method);
            }
        }
    }
}
