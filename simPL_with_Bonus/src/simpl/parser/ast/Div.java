package simpl.parser.ast;

import simpl.interpreter.IntValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;

public class Div extends ArithExpr {

    public Div(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " / " + r + ")";
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        IntValue v1 = (IntValue) l.forceEval(s);
        IntValue v2 = (IntValue) r.forceEval(s);
        if (v2.n == 0) throw new  RuntimeError("Divided by zero error!");
        else return new IntValue(v1.n/v2.n);
    }
}
