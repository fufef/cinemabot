package botLogic.commands;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class HelpCommand {
    public static String getHelpForCommand(List<Command> commands, String commandName) {
        String descriptions = getHelpForCommandsSatisfyingCondition(
                commands,
                command -> command.name().equals(commandName));
        if (descriptions.isEmpty())
            return String.format("Неизвестная команда %s", commandName);
        return descriptions.substring(0, descriptions.length() - 2);
    }

    public static String getHelpForAllCommands(List<Command> commands) {
        String descriptions = getHelpForCommandsSatisfyingCondition(commands, command -> true);
        return descriptions.substring(0, descriptions.length() - 2);
    }

    private static String getHelpForCommandsSatisfyingCondition(List<Command> commands, Predicate<Command> condition) {
        StringBuilder descriptions = new StringBuilder();
        for (Command command : commands) {
            if (!condition.test(command))
                continue;
            descriptions
                    .append(command.name()).append(" ")
                    .append(command.arguments()).append("\n")
                    .append(command.description()).append("\n\n");
        }
        return descriptions.substring(0, descriptions.length() - 2);
    }
}
