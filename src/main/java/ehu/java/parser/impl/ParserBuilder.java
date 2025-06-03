package ehu.java.parser.impl;

import ehu.java.parser.Parser;

public class ParserBuilder {
    public static Parser buildParserChain() {
        Parser textParser = new TextParser();
        Parser paragraphParser = new ParagraphParser();
        Parser sentenceParser = new SentenceParser();
        Parser lexemeParser = new LexemeParser();

        textParser.setNext(paragraphParser);
        paragraphParser.setNext(sentenceParser);
        sentenceParser.setNext(lexemeParser);

        return textParser;
    }
}
