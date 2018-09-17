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

public class Not extends UnaryExpr {

    public Not(Expr e) {
        super(e);
    }

    public String toString() {
        return "(not " + e + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult r = e.typecheck(E);
        Substitution s = ((r.s.apply(r.t)).unify(Type.BOOL)).compose(r.s);
        return  TypeResult.of(s,Type.BOOL);
        //return null;
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        BoolValue v = (BoolValue) e.eval(s);
        return new BoolValue(!v.b);
        //return null;
    }
}
