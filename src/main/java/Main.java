import commandHandler.CommandHandler;
import inputModule.ConsoleInput;
import outputModule.ConsoleOutput;
import tokenizer.Tokenizer;

public class Main {
    public static void main(String[] args) {
        new CommandHandler(
                new Tokenizer(new ConsoleInput()),
                new ConsoleOutput());
    }
}