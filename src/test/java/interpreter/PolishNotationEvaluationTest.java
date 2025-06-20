package interpreter;

import ehu.java.interpreter.Client;
import ehu.java.interpreter.MathExpression;
import ehu.java.interpreter.PolishNotationParser;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class PolishNotationEvaluationTest {
    private final PolishNotationParser parser = new PolishNotationParser();
    private final Client client = new Client();

    @Test
    void testSimpleAddition() {
        List<MathExpression> expressions = parser.parse("3 4 +");
        double result = client.handleExpression(expressions);
        assertEquals(7.0, result, 1e-9);
    }

    @Test
    void testMixedOperations() {
        List<MathExpression> expressions = parser.parse("5 1 2 + 4 * + 3 -");
        double result = client.handleExpression(expressions);
        assertEquals(14.0, result, 1e-9);
    }

    @Test
    void testDivisionAndMultiplication() {
        List<MathExpression> expressions = parser.parse("10 2 / 3 *");
        double result = client.handleExpression(expressions);
        assertEquals(15.0, result, 1e-9);
    }

    @Test
    void testUnaryMinus() {
        List<MathExpression> expressions = parser.parse("-5 3 *");
        double result = client.handleExpression(expressions);
        assertEquals(-15.0, result, 1e-9);
    }

    @Test
    void testComplexExpression() {
        List<MathExpression> expressions = parser.parse(
                "-71 2 3 3 2 1 2 / 2 + + * 2 - / / 10 / 2 + + 7 /"
        );
        double result = client.handleExpression(expressions);
        assertEquals(-9.747619047619049, result, 1e-9);
    }

    @Test
    void testDecimalOperations() {
        List<MathExpression> expressions = parser.parse("2.5 3.1 * 1.8 +");
        double result = client.handleExpression(expressions);
        assertEquals(9.55, result, 1e-9);
    }

    @Test
    void testDivisionByZero() {
        List<MathExpression> expressions = parser.parse("5 0 /");
        assertThrows(ArithmeticException.class, () -> {
            client.handleExpression(expressions);
        });
    }
}