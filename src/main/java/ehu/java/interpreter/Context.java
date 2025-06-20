package ehu.java.interpreter;

import java.util.ArrayDeque;

public class Context {
    private ArrayDeque<Double> contextValue = new ArrayDeque<>();

    public Double pop() {
        return contextValue.pop();
    }

    public void push(Double number) {
        contextValue.push(number);
    }

    public int size() {
        return contextValue.size();
    }
}