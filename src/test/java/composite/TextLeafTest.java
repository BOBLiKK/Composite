package composite;

import ehu.java.composite.TextComponent;
import ehu.java.composite.impl.ComponentType;
import ehu.java.composite.impl.TextLeaf;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TextLeafTest {
    @Test
    void getTypeReturnsCorrectType() {
        TextComponent leaf = new TextLeaf('X', ComponentType.SYMBOL);
        assertEquals(ComponentType.SYMBOL, leaf.getType());
    }

    @Test
    void toStringReturnsSymbolAsString() {
        TextComponent leaf = new TextLeaf('Y', ComponentType.SYMBOL);
        assertEquals("Y", leaf.toString());
    }

    @Test
    void addThrowsException() {
        TextComponent leaf = new TextLeaf('Z', ComponentType.SYMBOL);
        assertThrows(UnsupportedOperationException.class, () -> leaf.add(new TextLeaf('A', ComponentType.SYMBOL)));
    }

    @Test
    void removeThrowsException() {
        TextComponent leaf = new TextLeaf('Z', ComponentType.SYMBOL);
        assertThrows(UnsupportedOperationException.class, () -> leaf.remove(new TextLeaf('A', ComponentType.SYMBOL)));
    }

    @Test
    void getChildThrowsException() {
        TextComponent leaf = new TextLeaf('Z', ComponentType.SYMBOL);
        assertThrows(UnsupportedOperationException.class, leaf::getChild);
    }
}
