package simpl.parser.ast;

import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Seq extends BinaryExpr {

    public Seq(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " ; " + r + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult r1 = l.typecheck(E);
        TypeResult r2 = r.typecheck(r1.s.compose(E));
        return TypeResult.of(r2.s.compose(r1.s),r2.t);
        //return null;
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        l.forceEval(s);
        return r.forceEval(s);
    }
}
