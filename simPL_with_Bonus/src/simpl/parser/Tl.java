package simpl.parser.ast;

import simpl.interpreter.*;
import simpl.parser.Symbol;
import simpl.typing.*;

public class Tl extends Expr {

    public Expr e;

    public Tl(Expr e) {
        this.e = e;
    }

    public String toString() {
        return e + ".L";
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
            return ((ConsValue)v).v2;
        }
        throw new RuntimeError("parameter of tl is not List!");

        //return null;
    }
}
