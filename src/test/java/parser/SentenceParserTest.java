package parser;

import ehu.java.composite.TextComponent;
import ehu.java.composite.impl.ComponentType;
import ehu.java.parser.impl.SentenceParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SentenceParserTest {
    @Test
    void sentenceParserSplitsLexemesCorrectly() {
        SentenceParser parser = new SentenceParser();
        parser.setNext(new StubParser(ComponentType.LEXEME));

        String input = "Hello world!";
        TextComponent component = parser.parse(input);
        assertEquals(ComponentType.SENTENCE, component.getType());
        assertEquals(2, component.getChild().size());
    }
}
