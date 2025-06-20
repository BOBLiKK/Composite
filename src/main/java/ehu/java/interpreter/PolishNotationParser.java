package ehu.java.interpreter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PolishNotationParser {
    public List<MathExpression> parse(String polishNotation) {
        List<MathExpression> expression = new ArrayList<>();
        List<String> tokens = Arrays.asList(polishNotation.split("\\s+"));

        for (String token : tokens) {
            if (token.isEmpty()) continue;

            switch (token) {
                case "+":
                    expression.add(c -> {
                        double b = c.pop();
                        double a = c.pop();
                        c.push(a + b);
                    });
                    break;
                case "-":
                    expression.add(c -> {
                        if (c.size() < 2) throw new RuntimeException("Not enough operands");
                        double b = c.pop();
                        double a = c.pop();
                        c.push(a - b);
                    });
                    break;
                case "*":
                    expression.add(c -> {
                        double b = c.pop();
                        double a = c.pop();
                        c.push(a * b);
                    });
                    break;
                case "/":
                    expression.add(c -> {
                        double b = c.pop();
                        double a = c.pop();
                        if (b == 0) {
                            throw new ArithmeticException("Division by zero");
                        }
                        c.push(a / b);
                    });
                    break;
                default:
                    expression.add(c -> c.push(Double.parseDouble(token)));
            }
        }
        return expression;
    }
}