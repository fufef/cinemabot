package tokenizer;

import dataIO.inputModule.InputModule;
import dataIO.inputModule.Lexeme;

import java.util.Arrays;
import java.util.stream.Stream;

public record Tokenizer(InputModule inputModule) {

    public Token getTokenByLexem() {
        Lexeme lexeme = inputModule.getNextLexeme();
        return getTokenByLexem(lexeme);
    }

    public Token getTokenByLexem(Lexeme lexeme) {
        if (lexeme == null)
            return null;
        String[] parsedLexeme = parseLexeme(lexeme.lexeme());
        if (parsedLexeme.length == 0)
            return null;
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
        Stream<String> parsedLexeme = Arrays.stream(lexeme
                        .toLowerCase()
                        .replace("\n", "")
                        .replace("\r", "")
                        .split(" "))
                .filter(s -> !s.equals("") && !s.equals(" "));
        return parsedLexeme.toArray(String[]::new);
    }
}
