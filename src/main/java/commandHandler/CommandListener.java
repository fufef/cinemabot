package commandHandler;

import java.lang.reflect.Method;

public class CommandListener {
    @Command(
            name = "/help",
            arguments = "[command]",
            maxArgs = 1,
            description = "Выводит справку по командам")
    public String help(Object[] arguments) {
        if (arguments.length > 0)
            return helpForCommand(pullOutArguments(arguments)[0]);
        return helpForAllCommands();
    }

    private String helpForCommand(String commandName) {
        StringBuilder descriptions = new StringBuilder();
        for (Method method : this.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(Command.class)) {
                Command command = method.getAnnotation(Command.class);
                if (!command.name().equals(commandName))
                    continue;
                descriptions
                        .append(command.name()).append(" ")
                        .append(command.arguments()).append(" - ")
                        .append(command.description());
                break;
            }
        }
        if (descriptions.isEmpty())
            return String.format("Неизвестная команда %s", commandName);
        return descriptions.toString();
    }

    private String helpForAllCommands() {
        StringBuilder descriptions = new StringBuilder();
        for (Method method : this.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(Command.class)) {
                Command command = method.getAnnotation(Command.class);
                descriptions
                        .append(command.name()).append(" ")
                        .append(command.arguments()).append(" - ")
                        .append(command.description()).append("\n");
            }
        }
        return descriptions.toString();
    }

    private String[] pullOutArguments(Object[] arguments) {
        return (String[]) arguments;
    }
}
