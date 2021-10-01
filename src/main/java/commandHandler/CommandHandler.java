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
        String command = token.command();
        String[] arguments = token.arguments();
        Method methodForCommand = commands.get(command);
        if (methodForCommand == null) {
            outputModule.sendMessage(String.format("Неизвестная команда %s", command), token.userId());
            return;
        }
        try {
            String message = (String) methodForCommand.invoke(commandListener, new Object[]{arguments});
            outputModule.sendMessage(message, token.userId());
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
