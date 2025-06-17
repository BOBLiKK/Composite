package ehu.java.parser.impl;

import ehu.java.composite.TextComponent;
import ehu.java.composite.impl.*;

public class TextParser extends AbstractParser {
    private static final String PARAGRAPH_DELIMITER = "\\n(\\t| {4})";

    @Override
    public TextComponent parse(String data) {
        TextComposite textComposite = new TextComposite(ComponentType.TEXT);
        String[] paragraphs = data.strip().split(PARAGRAPH_DELIMITER);
        checkNextParser();
        for (String paragraph : paragraphs) {
            textComposite.add(nextParser.parse(paragraph.strip()));
        }
        return textComposite;
    }
}
