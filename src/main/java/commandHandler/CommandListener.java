package commandHandler;

import tokenizer.Token;

import java.lang.reflect.Method;

public class CommandListener {
    @Command(
            name = "/help",
            arguments = "[command]",
            description = "Выводит справку по командам")
    //TODO написать обработку аргументов
    public String help(Object[] arguments) {
        StringBuilder descriptions = new StringBuilder();
        for (Method m : this.getClass().getDeclaredMethods()) {
            if (m.isAnnotationPresent(Command.class)) {
                Command command = m.getAnnotation(Command.class);
                descriptions
                        .append(command.name()).append(" ")
                        .append(command.arguments()).append(" - ")
                        .append(command.description())/*.append("\n\n")*/;
            }
        }
        return descriptions.toString();
    }
}
