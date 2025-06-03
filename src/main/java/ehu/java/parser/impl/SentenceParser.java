package ehu.java.parser.impl;

import ehu.java.composite.TextComponent;
import ehu.java.composite.impl.*;

public class SentenceParser extends AbstractParser {
    private static final String LEXEME_DELIMITER = "\\s+";

    @Override
    public TextComponent parse(String data) {
        TextComposite sentenceComposite = new TextComposite(ComponentType.SENTENCE);
        String[] lexemes = data.strip().split(LEXEME_DELIMITER);
        checkNextParser();
        for (String lexeme : lexemes) {
            sentenceComposite.add(nextParser.parse(lexeme.strip()));
        }
        return sentenceComposite;
    }
}
