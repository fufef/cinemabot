import org.junit.Assert;
import org.junit.Test;
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
    public void nextLexemeIsMissing() {
        inputModule.setNewLexemes(new ArrayList<>());
        Assert.assertNull(tokenizer.getNextToken());
    }
}
