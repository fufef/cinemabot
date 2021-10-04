package tokenizer;

import inputModule.InputModule;
import inputModule.Lexeme;

import java.util.Arrays;

public record Tokenizer(InputModule inputModule) {

    public Token getNextToken() {
        Lexeme lexeme = inputModule.getNextLexeme();
        if (lexeme == null)
            return null;
        String[] parsedLexeme = parseLexeme(lexeme.lexeme());
        String command = parsedLexeme[0];
        String[] arguments = parsedLexeme.length > 1
                ? Arrays.copyOfRange(parsedLexeme, 1, parsedLexeme.length)
                : new String[0];
        return new Token(
                command,
                arguments,
                lexeme.userId());
    }

    private String[] parseLexeme(String lexeme) {
        return lexeme
                .toLowerCase()
                .replace("\n", "")
                .replace("\r", "")
                .split(" ");
    }
}
