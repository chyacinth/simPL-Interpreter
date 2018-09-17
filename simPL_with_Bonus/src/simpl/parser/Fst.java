package simpl.parser.ast;

import simpl.interpreter.*;
import simpl.parser.Symbol;
import simpl.typing.*;

public class Fst extends Expr {

    public Expr e;

    public Fst(Expr e) {
        this.e = e;
    }

    public String toString() {
        return e + ".1";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult r = e.typecheck(E);
        if (r.t instanceof PairType)
        {
            return TypeResult.of(r.s,((PairType) r.t).t1);
        }
        else throw new TypeMismatchError();
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        PairValue v = (PairValue) e.eval(s);
        return v.v1;
        //return null;
    }
}
