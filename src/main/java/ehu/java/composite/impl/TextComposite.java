package ehu.java.composite.impl;

import ehu.java.composite.TextComponent;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class TextComposite implements TextComponent {
    private final ComponentType type;
    private final List<TextComponent> components = new ArrayList<>();

    public TextComposite(ComponentType type) {
        this.type = type;
    }

    @Override
    public void add(TextComponent component) {
        components.add(component);
    }

    @Override
    public void remove(TextComponent component) {
        components.remove(component);
    }

    @Override
    public List<TextComponent> getChild() {
        return List.copyOf(components);
    }

    @Override
    public ComponentType getType() {
        return type;
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(getDelimiter());
        for (TextComponent component : components) {
            joiner.add(component.toString());
        }
        return joiner.toString();
    }


    //todo looks like something extremely incorrect
    private String getDelimiter() {
        return switch (type) {
            case TEXT -> "\n";
            case PARAGRAPH -> " ";
            case SENTENCE -> " ";
            case LEXEME -> "";
            default -> "";
        };
    }
}