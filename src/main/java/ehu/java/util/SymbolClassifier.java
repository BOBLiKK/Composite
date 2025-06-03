package ehu.java.util;

import ehu.java.composite.impl.ComponentType;

public class SymbolClassifier {
    public static ComponentType classify(char ch) {
        if (Character.isLetter(ch)) {
            return ComponentType.LETTER;
        } else if (Character.isDigit(ch)) {
            return ComponentType.NUMBER;
        } else {
            return ComponentType.PUNCTUATION;
        }
    }
}
