package ehu.java.interpreter;

import java.util.List;

public class MathExpressionResolver {

    private final PolishNotationParser parser = new PolishNotationParser();
    private final Client client = new Client();

    public boolean isMathExpression(String lexeme) {
        return lexeme.matches("[-+*/()\\d\\.]+") && lexeme.matches(".*[+\\-*/].*");
    }

    public String evaluate(String expression) {
        try {
            List<String> postfix = InfixToPostfixConverter.convert(expression);
            List<MathExpression> mathExpressions = parser.parse(String.join(" ", postfix));
            double result = client.handleExpression(mathExpressions);
            return Double.toString(result);
        } catch (Exception e) {
            return expression;
        }
    }
}
