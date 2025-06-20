package ehu.java.parser.impl;

import ehu.java.composite.TextComponent;
import ehu.java.composite.impl.*;
import ehu.java.util.SymbolClassifier;
import ehu.java.interpreter.MathExpressionResolver;

public class LexemeParser extends AbstractParser {

    private final MathExpressionResolver resolver = new MathExpressionResolver();

    @Override
    public TextComponent parse(String data) {
        TextComposite lexemeComposite = new TextComposite(ComponentType.LEXEME);

        String content = data.strip();
        if (resolver.isMathExpression(content)) {
            String result = resolver.evaluate(content);
            for (char ch : result.toCharArray()) {
                ComponentType type = SymbolClassifier.classify(ch);
                lexemeComposite.add(new TextLeaf(ch, type));
            }
            return lexemeComposite;
        }

        for (char ch : content.toCharArray()) {
            ComponentType type = SymbolClassifier.classify(ch);
            lexemeComposite.add(new TextLeaf(ch, type));
        }
        return lexemeComposite;
    }
}
