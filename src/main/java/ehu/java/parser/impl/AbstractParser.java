package ehu.java.parser.impl;

import ehu.java.parser.Parser;

public abstract class AbstractParser implements Parser {
    protected Parser nextParser;

    @Override
    public void setNext(Parser nextParser) {
        this.nextParser = nextParser;
    }

    protected void checkNextParser() {
        if (nextParser == null) {
            throw new IllegalStateException("Next parser is not set.");
        }
    }
}
