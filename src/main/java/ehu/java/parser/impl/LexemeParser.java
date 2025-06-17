package ehu.java.parser.impl;

import ehu.java.composite.TextComponent;
import ehu.java.composite.impl.*;
import ehu.java.util.SymbolClassifier;

public class LexemeParser extends AbstractParser {
    @Override
    public TextComponent parse(String data) {
        TextComposite lexemeComposite = new TextComposite(ComponentType.LEXEME);
        char[] characters = data.toCharArray();
        for (char ch : characters) {
            ComponentType type = SymbolClassifier.classify(ch);
            lexemeComposite.add(new TextLeaf(ch, type));
        }
        return lexemeComposite;
    }
}
