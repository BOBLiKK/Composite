package ehu.java.parser.impl;

import ehu.java.composite.TextComponent;
import ehu.java.composite.impl.*;

public class ParagraphParser extends AbstractParser {
    private static final String SENTENCE_DELIMITER = "(?<=[.!?…])\\s+";

    @Override
    public TextComponent parse(String data) {
        TextComposite paragraphComposite = new TextComposite(ComponentType.PARAGRAPH);
        String[] sentences = data.strip().split(SENTENCE_DELIMITER);
        checkNextParser();
        for (String sentence : sentences) {
            paragraphComposite.add(nextParser.parse(sentence.strip()));
        }
        return paragraphComposite;
    }
}
