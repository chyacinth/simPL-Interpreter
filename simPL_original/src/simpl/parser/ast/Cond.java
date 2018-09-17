package simpl.parser.ast;

import simpl.interpreter.BoolValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Cond extends Expr {

    public Expr e1, e2, e3;

    public Cond(Expr e1, Expr e2, Expr e3) {
        this.e1 = e1;
        this.e2 = e2;
        this.e3 = e3;
    }

    public String toString() {
        return "(if " + e1 + " then " + e2 + " else " + e3 + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult r1 = e1.typecheck(E);
        TypeResult r2 = e2.typecheck(r1.s.compose(E));
        TypeResult r3 = e3.typecheck(r2.s.compose(r1.s.compose(E)));
        Substitution s = r3.s.compose(r2.s.compose(r1.s));
        s = (s.apply(r1.t).unify(Type.BOOL)).compose(s);
        s = (s.apply(r2.t).unify(s.apply(r3.t))).compose(s);
        return (TypeResult.of(s,s.apply(r2.t)));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        boolean v1 = ((BoolValue)(e1.eval(s))).b;
        Value re;
        if (v1)
        {
            re = e2.eval(s);
        }
        else re = e3.eval(s);
        return re;
    }
}
