package botLogic;

import botLogic.commands.Command;
import botLogic.commands.CommandListener;
import tokenizer.Token;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class BotLogic {
    private final Map<String, Method> commands;
    private final CommandListener commandListener;

    public BotLogic() {
        commandListener = new CommandListener();
        commands = new HashMap<>();
        registerCommands();
    }

    public String handle(Token token) {
        String commandName = token.command();
        String[] arguments = token.arguments();
        Method methodForCommand = commands.get(commandName);
        if (methodForCommand == null) {
            return String.format("Неизвестная команда %s", commandName);
        }
        Command command = methodForCommand.getAnnotation(Command.class);
        if (arguments.length < command.minArgs() || arguments.length > command.maxArgs()) {
            return String.format(
                    "Команда %s принимает %d - %d аргументов",
                    command.name(),
                    command.minArgs(),
                    command.maxArgs());
        }
        try {
            return (String) methodForCommand.invoke(commandListener, new Object[]{arguments});
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
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
