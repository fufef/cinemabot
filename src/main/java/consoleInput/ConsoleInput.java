package consoleInput;

import commandHandler.CommandHandler;
import inputModule.InputModule;
import inputModule.Lexeme;

import java.util.Scanner;

public class ConsoleInput implements InputModule {
    private Scanner input;

    public ConsoleInput() {
        input = new Scanner(System.in);
    }

    @Override
    public Lexeme getNextLexeme() {
        System.out.print("--> ");
        String userInput = input.nextLine();
        return new Lexeme(userInput, "defaultID");
    }
}
