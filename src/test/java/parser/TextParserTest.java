package parser;

import ehu.java.composite.TextComponent;
import ehu.java.composite.impl.ComponentType;
import ehu.java.parser.impl.TextParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TextParserTest {

    @Test
    void textParserSplitsParagraphsCorrectly() {
        TextParser parser = new TextParser();
        parser.setNext(new StubParser(ComponentType.PARAGRAPH));

        String input = "\n\tFirst paragraph.\n\tSecond paragraph.";
        TextComponent component = parser.parse(input);

        assertEquals(ComponentType.TEXT, component.getType());
        assertEquals(2, component.getChild().size());
    }
}
