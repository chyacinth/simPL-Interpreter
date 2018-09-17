package simpl.parser.ast;

import simpl.interpreter.IntValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Neg extends UnaryExpr {

    public Neg(Expr e) {
        super(e);
    }

    public String toString() {
        return "~" + e;
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult r = e.typecheck(E);
        Substitution s = ((r.s.apply(r.t)).unify(Type.INT)).compose(r.s);
        return TypeResult.of(s,Type.INT);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        IntValue v = (IntValue) e.forceEval(s);
        return new IntValue(-v.n);
    }
}
