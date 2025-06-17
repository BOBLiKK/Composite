package parser;

import ehu.java.composite.TextComponent;
import ehu.java.composite.impl.ComponentType;
import ehu.java.parser.impl.LexemeParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LexemeParserTest {
    @Test
    void lexemeParserSplitsSymbolsCorrectly() {
        LexemeParser parser = new LexemeParser();

        String input = "Hi!";
        TextComponent component = parser.parse(input);

        assertEquals(ComponentType.LEXEME, component.getType());
        assertEquals(3, component.getChild().size());

        assertEquals("H", component.getChild().get(0).toString());
        assertEquals("i", component.getChild().get(1).toString());
        assertEquals("!", component.getChild().get(2).toString());
    }
}
