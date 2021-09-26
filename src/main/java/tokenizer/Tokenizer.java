package tokenizer;

import inputModule.InputModule;
import inputModule.Lexeme;

public class Tokenizer {
    private final InputModule inputModule;

    public Tokenizer(InputModule inputModule) {
        this.inputModule = inputModule;
    }

    public Token getNextToken() {
        Lexeme lexeme = inputModule.getNextLexeme();
        if (lexeme == null) return null;
        return new Token(
                parseLexeme(lexeme.lexeme()),
                lexeme.userId());
    }

    private String[] parseLexeme(String lexeme) {
        return lexeme
                .replace("\r", "")
                .replace("\r", "")
                .split(" ");
    }
}
