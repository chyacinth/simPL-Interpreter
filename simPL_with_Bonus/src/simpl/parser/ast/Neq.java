package simpl.parser.ast;

import simpl.interpreter.BoolValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;

public class Neq extends EqExpr {

    public Neq(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " <> " + r + ")";
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        Value v1 = l.forceEval(s);
        Value v2 = r.forceEval(s);
        return new BoolValue(!v1.equals(v2));
        //return null;
    }
}
