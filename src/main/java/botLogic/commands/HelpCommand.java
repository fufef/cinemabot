package botLogic.commands;

import java.util.List;
import java.util.function.Predicate;

public class HelpCommand {
    public static String getHelpForCommand(List<Command> commands, String commandName) {
        String descriptions = getHelpForCommandsSatisfyingCondition(
                commands,
                command -> command.name().equals(commandName));
        if (descriptions.isEmpty())
            return String.format("Неизвестная команда %s", commandName);
        return descriptions;
    }

    public static String getHelpForAllCommands(List<Command> commands) {
        return getHelpForCommandsSatisfyingCondition(commands, command -> true);
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
