package simpl.parser.ast;

import simpl.interpreter.*;
import simpl.parser.Symbol;
import simpl.typing.*;

public class Snd extends Expr {

    public Expr e;

    public Snd(Expr e) {
        this.e = e;
    }

    public String toString() {
        return e + ".2";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult r = e.typecheck(E);
        if (r.t instanceof PairType)
        {
            return TypeResult.of(r.s,((PairType) r.t).t2);
        }
        else throw new TypeMismatchError();
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        PairValue v = (PairValue) e.eval(s);
        return v.v2;
        //return null;
    }
}
