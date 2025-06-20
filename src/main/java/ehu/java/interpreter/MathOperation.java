package ehu.java.interpreter;

public enum MathOperation {
    MINUS('-'),
    PLUS('+'),
    MULTIPLY('*'),
    DIVIDE('/');

    private final char symbol;

    MathOperation(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public static MathOperation fromSymbol(char symbol) {
        for (MathOperation op : values()) {
            if (op.symbol == symbol) {
                return op;
            }
        }
        throw new IllegalArgumentException("Unknown operation: " + symbol);
    }
}
