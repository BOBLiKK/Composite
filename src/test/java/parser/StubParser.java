package parser;

import ehu.java.composite.TextComponent;
import ehu.java.composite.impl.ComponentType;
import ehu.java.composite.impl.TextComposite;
import ehu.java.parser.Parser;

public class StubParser implements Parser {
    private final ComponentType type;

    public StubParser(ComponentType type) {
        this.type = type;
    }

    @Override
    public TextComponent parse(String data) {
        return new TextComposite(type);
    }

    @Override
    public void setNext(Parser nextParser) {
    }
}
