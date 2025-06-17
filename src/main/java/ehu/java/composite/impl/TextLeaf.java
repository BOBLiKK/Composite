package ehu.java.composite.impl;

import ehu.java.composite.TextComponent;
import java.util.List;

public class TextLeaf implements TextComponent {
    private final char symbol;
    private final ComponentType type;

    public TextLeaf(char symbol, ComponentType type) {
        this.symbol = symbol;
        this.type = type;
    }

    @Override
    public void add(TextComponent component) {
        throw new UnsupportedOperationException("Cannot add to a leaf");
    }

    @Override
    public void remove(TextComponent component) {
        throw new UnsupportedOperationException("Cannot remove from a leaf");
    }

    @Override
    public List<TextComponent> getChild() {
        throw new UnsupportedOperationException("Cannot get the child of a leaf");
    }

    @Override
    public ComponentType getType() {
        return type;
    }

    @Override
    public String toString() {
        return String.valueOf(symbol);
    }
}
