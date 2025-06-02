package ehu.java.composite.impl;

import ehu.java.composite.TextComponent;
import java.util.List;

public class Symbol implements TextComponent {
    private final char symbol;
    private final ComponentType type;

    public Symbol(char symbol, ComponentType type) {
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
        return List.of();
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
