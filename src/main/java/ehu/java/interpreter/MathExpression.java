package ehu.java.interpreter;

import java.util.function.Consumer;

@FunctionalInterface
public interface MathExpression extends Consumer<Context> {
}
