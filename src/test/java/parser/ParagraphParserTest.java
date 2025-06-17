package parser;

import ehu.java.composite.TextComponent;
import ehu.java.composite.impl.ComponentType;
import ehu.java.parser.impl.ParagraphParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParagraphParserTest {
    @Test
    void paragraphParserSplitsSentencesCorrectly() {
        ParagraphParser parser = new ParagraphParser();
        parser.setNext(new StubParser(ComponentType.SENTENCE));

        String input = "First sentence. Second sentence?";
        TextComponent component = parser.parse(input);

        assertEquals(ComponentType.PARAGRAPH, component.getType());
        assertEquals(2, component.getChild().size());
    }
}
