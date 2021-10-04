package inputModuleForTests;

import inputModule.InputModule;
import inputModule.Lexeme;

import java.util.List;
import java.util.ListIterator;

public class InputModuleForTests implements InputModule {
    private ListIterator<Lexeme> iterator;

    public InputModuleForTests(List<Lexeme> lexemes) {
        setNewLexemes(lexemes);
    }

    public void setNewLexemes(List<Lexeme> lexemes) {
        this.iterator = lexemes.listIterator();
    }

    @Override
    public Lexeme getNextLexeme() {
        if (!iterator.hasNext())
            return null;
        return iterator.next();
    }
}
