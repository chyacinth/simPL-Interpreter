package simpl.parser.ast;

import simpl.interpreter.ConsValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.*;

public class Cons extends BinaryExpr {

    public Cons(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " :: " + r + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult r1 = l.typecheck(E);
        TypeResult r2 = r.typecheck(r1.s.compose(E));
        Substitution s = r2.s.compose(r1.s), s1;
        s1 = s.apply(r2.t).unify(new ListType(s.apply(r1.t)));
        s = s1.compose(s);
        return TypeResult.of(s,s.apply(r2.t));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        return new ConsValue(l.forceEval(s),r.forceEval(s));
        //return null;
    }
}
