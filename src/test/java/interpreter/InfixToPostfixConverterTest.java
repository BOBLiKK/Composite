package interpreter;


import ehu.java.interpreter.InfixToPostfixConverter;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class InfixToPostfixConverterTest {

    @Test
    void testSimpleExpression() {
        List<String> result = InfixToPostfixConverter.convert("1+2*3");
        assertEquals(List.of("1", "2", "3", "*", "+"), result);
    }

    @Test
    void testExpressionWithParentheses() {
        List<String> result = InfixToPostfixConverter.convert("(1+2)*3");
        assertEquals(List.of("1", "2", "+", "3", "*"), result);
    }

    @Test
    void testUnaryMinus() {
        List<String> result = InfixToPostfixConverter.convert("-5+3");
        assertEquals(List.of("-5", "3", "+"), result);
    }

    @Test
    void testNestedUnaryMinus() {
        List<String> result = InfixToPostfixConverter.convert("(-5+3)*2");
        assertEquals(List.of("-5", "3", "+", "2", "*"), result);
    }

    @Test
    void testComplexExpression() {
        List<String> result = InfixToPostfixConverter.convert("3+4*2/(1-5)");
        assertEquals(List.of("3", "4", "2", "*", "1", "5", "-", "/", "+"), result);
    }

    @Test
    void testDecimalNumbers() {
        List<String> result = InfixToPostfixConverter.convert("2.5*3.1+1.8");
        assertEquals(List.of("2.5", "3.1", "*", "1.8", "+"), result);
    }

    @Test
    void testComplexExpressionWithUnary() {
        String expression = "(-71+(2+3/(3*(2+1/2+2)-2)/10+2))/7";
        List<String> expected = List.of(
                "-71", "2", "3", "3", "2", "1", "2", "/", "+", "2",  "+", "*", "2", "-", "/", "10", "/",
                "+", "2",  "+", "+", "7", "/"

        );
        List<String> result = InfixToPostfixConverter.convert(expression);
        assertEquals(expected, result);
    }

    @Test
    void testUnbalancedParentheses() {
        assertThrows(IllegalArgumentException.class, () -> {
            InfixToPostfixConverter.convert("(1+2))");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            InfixToPostfixConverter.convert("((1+2)");
        });
    }
}