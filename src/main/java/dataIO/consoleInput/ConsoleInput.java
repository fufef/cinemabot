package dataIO.consoleInput;

import dataIO.inputModule.InputModule;
import dataIO.inputModule.Lexeme;

import java.util.Scanner;

public class ConsoleInput implements InputModule {
    private final Scanner input;

    public ConsoleInput() {
        input = new Scanner(System.in);
    }

    @Override
    public Lexeme getNextLexeme() {
        System.out.print(">>>");
        String userInput = input.nextLine();
        return new Lexeme(userInput, "Bob");//"defaultID");
    }
}
