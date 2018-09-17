package simpl.parser.ast;

import simpl.interpreter.RefValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.RefType;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Assign extends BinaryExpr {

    public Assign(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return l + " := " + r;
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult r1 = l.typecheck(E);
        TypeResult r2 = r.typecheck(r1.s.compose(E));
        Substitution s = r2.s.compose(r1.s);
        s = (s.apply(r1.t).unify(new RefType(s.apply(r2.t)))).compose(s);
        return TypeResult.of(s,Type.UNIT);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        RefValue v1 = (RefValue) l.forceEval(s);
        Value v2 = r.forceEval(s);
        s.M.put(v1.p,v2);
        return Value.UNIT;
        //return null;
    }
}
