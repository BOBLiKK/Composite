package composite;

import ehu.java.composite.TextComponent;
import ehu.java.composite.impl.ComponentType;
import ehu.java.composite.impl.TextComposite;
import ehu.java.composite.impl.TextLeaf;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class TextCompositeTest {

    @Test
    void addChildAddsComponent() {
        TextComposite composite = new TextComposite(ComponentType.SENTENCE);
        TextComponent child = new TextLeaf( 'A', ComponentType.SYMBOL);

        composite.add(child);

        assertEquals(1, composite.getChild().size());
        assertSame(child, composite.getChild().get(0));
    }

    @Test
    void removeChildRemovesComponent() {
        TextComposite composite = new TextComposite(ComponentType.SENTENCE);
        TextComponent child = new TextLeaf('B', ComponentType.SYMBOL);
        composite.add(child);

        composite.remove(child);

        assertEquals(0, composite.getChild().size());
    }

    @Test
    void getTypeReturnsCorrectType() {
        TextComposite composite = new TextComposite(ComponentType.PARAGRAPH);
        assertEquals(ComponentType.PARAGRAPH, composite.getType());
    }

    @Test
    void toStringRestoresTextCorrectly() {
        TextComposite composite = new TextComposite(ComponentType.LEXEME);
        composite.add(new TextLeaf('H', ComponentType.SYMBOL));
        composite.add(new TextLeaf('i', ComponentType.SYMBOL));
        composite.add(new TextLeaf('!', ComponentType.SYMBOL));

        String result = composite.toString();
        assertEquals("Hi!", result);
    }

    @Test
    void compositeRestoresFullTextCorrectly() {
        TextComposite text = new TextComposite(ComponentType.TEXT);

        TextComposite paragraph1 = new TextComposite(ComponentType.PARAGRAPH);
        TextComposite sentence1 = new TextComposite(ComponentType.SENTENCE);
        TextComposite lexeme1 = new TextComposite(ComponentType.LEXEME);
        lexeme1.add(new TextLeaf('H', ComponentType.SYMBOL));
        lexeme1.add(new TextLeaf('e', ComponentType.SYMBOL));
        lexeme1.add(new TextLeaf('l', ComponentType.SYMBOL));
        lexeme1.add(new TextLeaf('l', ComponentType.SYMBOL));
        lexeme1.add(new TextLeaf('o', ComponentType.SYMBOL));
        TextComposite lexeme2 = new TextComposite(ComponentType.LEXEME);
        lexeme2.add(new TextLeaf('w', ComponentType.SYMBOL));
        lexeme2.add(new TextLeaf('o', ComponentType.SYMBOL));
        lexeme2.add(new TextLeaf('r', ComponentType.SYMBOL));
        lexeme2.add(new TextLeaf('l', ComponentType.SYMBOL));
        lexeme2.add(new TextLeaf('d', ComponentType.SYMBOL));
        lexeme2.add(new TextLeaf('!', ComponentType.SYMBOL));
        sentence1.add(lexeme1);
        sentence1.add(lexeme2);
        paragraph1.add(sentence1);

        TextComposite paragraph2 = new TextComposite(ComponentType.PARAGRAPH);
        TextComposite sentence2 = new TextComposite(ComponentType.SENTENCE);
        TextComposite lexeme3 = new TextComposite(ComponentType.LEXEME);
        lexeme3.add(new TextLeaf('T', ComponentType.SYMBOL));
        lexeme3.add(new TextLeaf('h', ComponentType.SYMBOL));
        lexeme3.add(new TextLeaf('i', ComponentType.SYMBOL));
        lexeme3.add(new TextLeaf('s', ComponentType.SYMBOL));
        lexeme3.add(new TextLeaf(' ', ComponentType.SYMBOL));
        lexeme3.add(new TextLeaf('i', ComponentType.SYMBOL));
        lexeme3.add(new TextLeaf('s', ComponentType.SYMBOL));
        lexeme3.add(new TextLeaf('.', ComponentType.SYMBOL));
        sentence2.add(lexeme3);
        paragraph2.add(sentence2);

        text.add(paragraph1);
        text.add(paragraph2);

        String expected = "\tHello world!\n\tThis is.";
        String actual = text.toString();

        assertEquals(expected, actual);
    }

}
