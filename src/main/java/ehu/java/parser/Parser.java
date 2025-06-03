package ehu.java.parser;

import ehu.java.composite.TextComponent;

public interface Parser {
    void setNext(Parser nextParser);
    TextComponent parse(String data);
}
