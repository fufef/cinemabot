package tokenizerTests;

import inputModuleForTests.InputModuleForTests;
import inputModule.Lexeme;
import org.junit.Assert;
import org.junit.Test;
import tokenizer.Token;
import tokenizer.Tokenizer;

import java.util.ArrayList;

public class TokenizerTests {
    private static final InputModuleForTests inputModule;
    private static final Tokenizer tokenizer;

    static {
        inputModule = new InputModuleForTests(new ArrayList<>());
        tokenizer = new Tokenizer(inputModule);
    }

    @Test
    public void getNextToken_NextLexemeIsMissing_Null() {
        inputModule.setNewLexemes(new ArrayList<>());
        Assert.assertNull(tokenizer.getNextToken());
    }

    @Test
    public void getNextToken_LexemeIsEmpty_TokenWithEmptyCommandAndNoArguments() {
        String userID = "userID";
        ArrayList<Lexeme> lexemes = new ArrayList<>();
        lexemes.add(new Lexeme("", userID));
        inputModule.setNewLexemes(lexemes);

        Token actualToken = tokenizer.getNextToken();

        areExpectedTokenAndActualTokenEqual(null, actualToken);
    }

    protected static void areExpectedTokenAndActualTokenEqual(Token expectedToken, Token actualToken) {
        if (expectedToken == null) {
            Assert.assertNull(actualToken);
            return;
        }
        Assert.assertNotNull(actualToken);
        Assert.assertEquals(expectedToken.command(), actualToken.command());
        Assert.assertArrayEquals(expectedToken.arguments(), actualToken.arguments());
        Assert.assertEquals(expectedToken.userId(), actualToken.userId());
    }
}
