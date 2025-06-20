package ehu.java.interpreter;

import java.util.List;

public class Client {
    private Context context = new Context();
    public double handleExpression(List<MathExpression> expression){
        expression.forEach(t -> t.accept(context));
        return context.pop();
    }
}
