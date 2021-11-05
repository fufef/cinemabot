package botLogic.commands;

public class CommandException extends RuntimeException {
    public CommandException(String errorMessage) {
        super(errorMessage);
    }
}