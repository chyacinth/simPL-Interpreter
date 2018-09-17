package simpl.parser.ast;

import com.sun.corba.se.impl.io.TypeMismatchException;
import simpl.interpreter.RecValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.*;

public class Name extends Expr {

    public Symbol x;

    public Name(Symbol x) {
        this.x = x;
    }

    public String toString() {
        return "" + x;
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        if (E.get(x) == null)
        {
            //System.out.println("Type Error! No name!\n");
            //throw new TypeError("Type Error! No name!\n");
            throw new TypeMismatchError();

        }
        else return TypeResult.of(E.get(x));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        //Value v = s.E.get(x);
        /*if (v instanceof RecValue)
        {
            return ((RecValue)v).e.eval(s);
        }
        else return v;*/
        //return null;
/*
        if(v instanceof RecValue){
            Rec e = new Rec(((RecValue) v).x,((RecValue) v).e);
            return e.eval(State.of(((RecValue) v).E,s.M,s.p));
        }
        else{
            return v;
        }
*/
        Value v1 = s.E.get(x);
        if (v1 instanceof RecValue){
            RecValue nv1 = (RecValue)v1;
            Rec r = new Rec(nv1.x, nv1.e);
            return r.eval(State.of(nv1.E, s.M, s.p));
        }
        return v1;
    }
}
