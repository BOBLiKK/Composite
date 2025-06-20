package interpreter;

import ehu.java.interpreter.MathExpressionResolver;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MathExpressionResolverTest {
    private final MathExpressionResolver resolver = new MathExpressionResolver();

    @Test
    void testFullPipeline() {
        String result = resolver.evaluate("(-71+(2+3/(3*(2+1/2+2)-2))/10+2)/7");
        assertEquals("-9.824844720496895", result);
    }

    @Test
    void testInvalidExpression() {
        String result = resolver.evaluate("not a math expression");
        assertEquals("not a math expression", result);
    }

    @Test
    void testSimpleExpression() {
        String result = resolver.evaluate("2+2*2");
        assertEquals("6.0", result);
    }

    @Test
    void testWithUnaryMinus() {
        String result = resolver.evaluate("-5*3+2");
        assertEquals("-13.0", result);
    }
}