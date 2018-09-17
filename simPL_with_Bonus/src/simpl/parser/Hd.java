package simpl.parser.ast;

import simpl.interpreter.*;
import simpl.parser.Symbol;
import simpl.typing.*;

public class Hd extends Expr {

    public Expr e;

    public Hd(Expr e) {
        this.e = e;
    }

    public String toString() {
        return e + ".H";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        throw new TypeMismatchError();
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        Value v = e.eval(s);
        if (v instanceof ConsValue)
        {
            return ((ConsValue)v).v1;
        }
        throw new RuntimeError("parameter of hd is not List!");

        //return null;
    }
}
