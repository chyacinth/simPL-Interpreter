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

public class AndAlso extends BinaryExpr {

    public AndAlso(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " andalso " + r + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult r1 = l.typecheck(E);
        TypeResult r2 = r.typecheck(r1.s.compose(E));
        Substitution s = r2.s.compose(r1.s);
        s = (s.apply(r1.t).unify(Type.BOOL)).compose(s);
        s = (s.apply(r2.t).unify(Type.BOOL)).compose(s);
        return TypeResult.of(s,Type.BOOL);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        BoolValue v1 =(BoolValue) l.eval(s);
        if (v1.b == true)
        {
            return r.eval(s);
        }
        else return new BoolValue(false);
        //return null;
    }
}
