package ehu.java.composite;

import ehu.java.composite.impl.ComponentType;

import java.util.List;

public interface TextComponent {
    void add(TextComponent component);
    void remove(TextComponent component);
    List<TextComponent> getChild();
    ComponentType getType();
    String toString();
}
