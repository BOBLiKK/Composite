package ehu.java.interpreter;

import java.util.*;

public class InfixToPostfixConverter {
    private static final Set<String> OPERATORS = Set.of("+", "-", "*", "/");

    private static boolean isOperator(String token) {
        return OPERATORS.contains(token);
    }

    private static int getPriority(String op) {
        return switch (op) {
            case "+", "-" -> 1;
            case "*", "/" -> 2;
            default -> -1;
        };
    }

    public static List<String> convert(String infix) {
        List<String> output = new ArrayList<>();
        Deque<String> stack = new ArrayDeque<>();

        StringBuilder number = new StringBuilder();
        boolean expectUnary = true;

        for (int i = 0; i < infix.length(); i++) {
            char ch = infix.charAt(i);

            if (Character.isWhitespace(ch)) continue;

            if (Character.isDigit(ch) || ch == '.' || (ch == '-' && expectUnary)) {
                number.append(ch);
                expectUnary = false;
            } else {
                flushNumber(number, output);

                if (isOperator(String.valueOf(ch))) {
                    while (!stack.isEmpty() &&
                            !stack.peek().equals("(") &&
                            getPriority(String.valueOf(ch)) <= getPriority(stack.peek())) {
                        output.add(stack.pop());
                    }
                    stack.push(String.valueOf(ch));
                    expectUnary = true;
                } else if (ch == '(') {
                    stack.push("(");
                    expectUnary = true;
                } else if (ch == ')') {
                    while (!stack.isEmpty() && !stack.peek().equals("(")) {
                        output.add(stack.pop());
                    }
                    if (stack.isEmpty()) {
                        throw new IllegalArgumentException("Unbalanced parentheses");
                    }
                    stack.pop(); // Remove '('
                    expectUnary = false;
                }
            }
        }

        flushNumber(number, output);
        while (!stack.isEmpty()) {
            if (stack.peek().equals("(")) {
                throw new IllegalArgumentException("Unbalanced parentheses");
            }
            output.add(stack.pop());
        }

        return output;
    }

    private static void flushNumber(StringBuilder number, List<String> output) {
        if (number.length() > 0) {
            output.add(number.toString());
            number.setLength(0);
        }
    }
}