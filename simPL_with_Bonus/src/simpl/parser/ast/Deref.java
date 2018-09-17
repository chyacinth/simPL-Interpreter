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
import simpl.typing.TypeVar;

public class Deref extends UnaryExpr {

    public Deref(Expr e) {
        super(e);
    }

    public String toString() {
        return "!" + e;
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult r = e.typecheck(E);
        TypeVar tv = new TypeVar(true);
        Substitution s = (r.t.unify(new RefType(tv))).compose(r.s);
        return TypeResult.of(s,s.apply(tv));
        //return null;
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        Value p = e.forceEval(s);
        if (p instanceof RefValue)
        {
            return s.M.get(((RefValue) p).p);
        }
        else throw new RuntimeError("Runtime Error!");
        //return null;
    }
}
